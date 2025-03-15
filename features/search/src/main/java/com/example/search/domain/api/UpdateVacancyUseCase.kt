package com.example.search.domain.api

import com.example.common.domain.model.Vacancy

interface UpdateVacancyUseCase {
    suspend fun update(vacancy: Vacancy)
}