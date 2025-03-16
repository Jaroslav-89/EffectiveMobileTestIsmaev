package com.example.favorite.domain.impl

import com.example.common.domain.model.Vacancy
import com.example.favorite.domain.api.FavoriteVacanciesRepository
import com.example.favorite.domain.api.GetFavoriteVacanciesUseCase
import kotlinx.coroutines.flow.Flow

class GetFavoriteVacanciesUseCaseImpl(
    private val favoriteVacanciesRepository: FavoriteVacanciesRepository
) : GetFavoriteVacanciesUseCase {
    override fun get(): Flow<List<Vacancy>> {
        return favoriteVacanciesRepository.getFavoriteVacancies()
    }
}