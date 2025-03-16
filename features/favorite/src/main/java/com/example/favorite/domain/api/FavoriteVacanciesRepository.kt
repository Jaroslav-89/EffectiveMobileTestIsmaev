package com.example.favorite.domain.api

import com.example.common.domain.model.Vacancy
import kotlinx.coroutines.flow.Flow

interface FavoriteVacanciesRepository {
    fun getFavoriteVacancies(): Flow<List<Vacancy>>
    suspend fun updateVacancy(vacancy: Vacancy)
}