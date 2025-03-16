package com.example.search.domain.api

import kotlinx.coroutines.flow.Flow

interface GetFavoriteVacanciesIdUseCase {
    fun get(): Flow<List<String>>
}