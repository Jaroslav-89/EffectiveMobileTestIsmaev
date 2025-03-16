package com.example.effectivemobiletestismaev.domain.api

import kotlinx.coroutines.flow.Flow

interface GetFavoriteVacancyCountUseCase {
    fun invoke(): Flow<Int>
}