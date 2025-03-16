package com.example.effectivemobiletestismaev.domain.api

import kotlinx.coroutines.flow.Flow

interface GetFavoriteVacancyCountRepository {
    fun getFavoritesCount(): Flow<Int>
}