package com.example.favorite.data.repository

import android.util.Log
import com.example.common.domain.model.Vacancy
import com.example.database.AppDatabaseApi
import com.example.favorite.data.converter.VacancyEntityConverter
import com.example.favorite.domain.api.FavoriteVacanciesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteVacanciesRepositoryImpl(
    private val dataBase: AppDatabaseApi,
) : FavoriteVacanciesRepository {

    override fun getFavoriteVacancies(): Flow<List<Vacancy>> {
        return dataBase.favoritesDao.getAllVacancy().map(VacancyEntityConverter::convertList)
    }

    override suspend fun updateVacancy(vacancy: Vacancy) {
        Log.d("xxx", "delete ${vacancy.toString()}")
        if (vacancy.isFavorite)
            dataBase.favoritesDao.deleteFavoriteVacancy(VacancyEntityConverter.convert(vacancy))
    }
}