package com.example.favorite.domain.impl

import com.example.common.domain.model.Vacancy
import com.example.favorite.domain.api.FavoriteVacanciesRepository
import com.example.favorite.domain.api.UpdateFavoriteVacancyUseCase

class UpdateFavoriteVacancyUseCaseImpl(
    private val favoriteVacanciesRepository: FavoriteVacanciesRepository
) : UpdateFavoriteVacancyUseCase {
    override suspend fun update(vacancy: Vacancy) {
        favoriteVacanciesRepository.updateVacancy(vacancy)
    }
}