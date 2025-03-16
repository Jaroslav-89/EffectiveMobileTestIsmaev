package com.example.favorite.domain.api

import com.example.common.domain.model.Vacancy

interface UpdateFavoriteVacancyUseCase {
    suspend fun update(vacancy: Vacancy)
}