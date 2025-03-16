package com.example.favorite.domain.api

import com.example.common.domain.model.Vacancy
import kotlinx.coroutines.flow.Flow

interface GetFavoriteVacanciesUseCase {
    fun get(): Flow<List<Vacancy>>
}