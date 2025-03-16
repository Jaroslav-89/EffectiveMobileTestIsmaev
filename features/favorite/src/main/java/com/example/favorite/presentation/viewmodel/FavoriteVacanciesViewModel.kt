package com.example.favorite.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.domain.model.Vacancy
import com.example.favorite.domain.api.GetFavoriteVacanciesUseCase
import com.example.favorite.domain.api.UpdateFavoriteVacancyUseCase
import com.example.favorite.presentation.state.FavoriteScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteVacanciesViewModel(
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase,
    private val updateFavoriteVacancyUseCase: UpdateFavoriteVacancyUseCase,
) : ViewModel() {

    private val _screenState: MutableLiveData<FavoriteScreenState> =
        MutableLiveData(FavoriteScreenState.Loading)
    val screenState: LiveData<FavoriteScreenState> = _screenState

    init {
        getFavoriteVacancies()
    }

    fun onFavoriteClick(vacancy: Vacancy) {
        viewModelScope.launch(Dispatchers.IO) {
            updateFavoriteVacancyUseCase.update(vacancy)
        }
    }

    private fun getFavoriteVacancies() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteVacanciesUseCase.get().collect() {
                processResult(it)
            }
        }
    }

    private fun processResult(result: List<Vacancy>) {
        if (result.isNotEmpty())
            renderState(FavoriteScreenState.Content(vacancies = result))
        else
            renderState(FavoriteScreenState.VacanciesNotFound)
    }

    private fun renderState(state: FavoriteScreenState) {
        _screenState.postValue(state)
    }
}