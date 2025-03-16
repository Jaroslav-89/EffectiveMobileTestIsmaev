package com.example.search.domain.impl

import com.example.search.domain.api.GetFavoriteVacanciesIdUseCase
import com.example.search.domain.api.SearchRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteVacanciesIdUseCaseImpl(
    private val searchRepository: SearchRepository
) : GetFavoriteVacanciesIdUseCase {
    override fun get(): Flow<List<String>> {
        return searchRepository.getFavoriteVacanciesId()
    }
}