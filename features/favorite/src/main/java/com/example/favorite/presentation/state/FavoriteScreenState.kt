package com.example.favorite.presentation.state

import com.example.common.domain.model.Vacancy

interface FavoriteScreenState {

    object Loading : FavoriteScreenState

    data class Content(val vacancies: List<Vacancy>) : FavoriteScreenState

    object VacanciesNotFound : FavoriteScreenState
}