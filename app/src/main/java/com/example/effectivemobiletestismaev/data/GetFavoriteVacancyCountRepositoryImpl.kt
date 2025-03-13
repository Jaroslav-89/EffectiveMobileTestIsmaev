package com.example.effectivemobiletestismaev.data

import com.example.database.AppDatabaseApi
import com.example.effectivemobiletestismaev.domain.api.GetFavoriteVacancyCountRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteVacancyCountRepositoryImpl(
    private val database: AppDatabaseApi,
) :
    GetFavoriteVacancyCountRepository {
    override fun getFavoritesCount(): Flow<Int> {
        return database.favoritesDao.getFavoritesCount()
    }
}