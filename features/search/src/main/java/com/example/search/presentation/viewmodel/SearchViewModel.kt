package com.example.search.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.domain.model.Vacancy
import com.example.common.utills.NetworkResult
import com.example.search.domain.api.SearchUseCase
import com.example.search.domain.api.UpdateVacancyUseCase
import com.example.search.domain.model.Jobs
import com.example.search.presentation.state.SearchScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase,
    private val updateVacancyUseCase: UpdateVacancyUseCase,
) : ViewModel() {

    private val _screenState: MutableLiveData<SearchScreenState> =
        MutableLiveData(SearchScreenState.Loading)
    val screenState: LiveData<SearchScreenState> = _screenState
    private var jobsViewModel: Jobs = Jobs()

    init {
        getJobs()
    }

    fun onFavoriteClick(vacancy: Vacancy) {
        val index = jobsViewModel.vacancies.indexOfFirst { it.id == vacancy.id }
        if (index == -1) return
        val updatedVacancy = vacancy.copy(isFavorite = !vacancy.isFavorite)
        val updatedVacancies = jobsViewModel.vacancies.toMutableList().apply {
            this[index] = updatedVacancy
        }
        jobsViewModel = jobsViewModel.copy(vacancies = updatedVacancies)
        renderState(SearchScreenState.Content(jobsViewModel))

        viewModelScope.launch(Dispatchers.IO) {
            updateVacancyUseCase.update(updatedVacancy)
        }
    }

    private fun getJobs() {
        viewModelScope.launch(Dispatchers.IO) {
            searchUseCase.getJobs().collect() {
                processResult(it)
            }
        }
    }

    private fun processResult(result: NetworkResult<Jobs>) {
        when (result) {
            is NetworkResult.Success -> {
                val jobs = result.data
                if (jobs != null && jobs.vacancies.isNotEmpty()) {
                    jobsViewModel = jobs
                    renderState(SearchScreenState.Content(jobs = jobs))
                } else {
                    renderState(SearchScreenState.JobsNotFound)
                }
            }

            is NetworkResult.Error -> {
                renderState(SearchScreenState.SearchError(result.errorType!!))
            }
        }
    }

    private fun renderState(state: SearchScreenState) {
        _screenState.postValue(state)
    }
}