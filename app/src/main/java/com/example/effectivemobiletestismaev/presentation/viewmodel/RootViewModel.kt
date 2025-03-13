package com.example.effectivemobiletestismaev.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.effectivemobiletestismaev.domain.api.GetFavoriteVacancyCountUseCase
import kotlinx.coroutines.launch

class RootViewModel(private val useCase: GetFavoriteVacancyCountUseCase) : ViewModel() {

    private val _vacancyCount: MutableLiveData<Int> = MutableLiveData<Int>(0)
    val vacancyCount: LiveData<Int>
        get() = _vacancyCount

    init {
        viewModelScope.launch {
            useCase.invoke().collect {
                _vacancyCount.postValue(it)
            }
        }
    }
}