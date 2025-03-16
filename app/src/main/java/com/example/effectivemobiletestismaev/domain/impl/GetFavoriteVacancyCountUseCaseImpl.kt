package com.example.effectivemobiletestismaev.domain.impl

import com.example.effectivemobiletestismaev.domain.api.GetFavoriteVacancyCountRepository
import com.example.effectivemobiletestismaev.domain.api.GetFavoriteVacancyCountUseCase
import kotlinx.coroutines.flow.Flow

class GetFavoriteVacancyCountUseCaseImpl(
    private val repository: GetFavoriteVacancyCountRepository,
) :
    GetFavoriteVacancyCountUseCase {
    override fun invoke(): Flow<Int> {
        return repository.getFavoritesCount()
    }
}