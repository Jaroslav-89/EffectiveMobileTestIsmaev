package com.example.search.domain.impl

import com.example.common.domain.model.Vacancy
import com.example.search.domain.api.SearchRepository
import com.example.search.domain.api.UpdateVacancyUseCase

class UpdateVacancyUseCaseImpl(private val searchRepository: SearchRepository) : UpdateVacancyUseCase {
    override suspend fun update(vacancy: Vacancy) {
        searchRepository.updateVacancy(vacancy)
    }
}