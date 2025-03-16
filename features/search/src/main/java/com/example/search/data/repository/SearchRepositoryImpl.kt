package com.example.search.data.repository

import com.example.common.domain.model.Vacancy
import com.example.common.utills.ErrorType
import com.example.common.utills.NetworkResult
import com.example.database.AppDatabaseApi
import com.example.network.NetworkClient
import com.example.network.dto.JobsResponse
import com.example.search.data.converters.JobsDtoConverter
import com.example.search.data.converters.VacancyEntityConverter
import com.example.search.domain.api.SearchRepository
import com.example.search.domain.model.Jobs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val networkClient: NetworkClient,
    private val dataBase: AppDatabaseApi,
) : SearchRepository {

    override fun getJobs(): Flow<NetworkResult<Jobs>> = flow {
        val response = networkClient.doRequest()

        when (response.resultCode) {
            -1 -> {
                emit(NetworkResult.Error(ErrorType.NO_INTERNET))
            }

            200 -> {
                val data = JobsDtoConverter.convert(response as JobsResponse)
                emit(NetworkResult.Success(data = data))

                if (data.vacancies.isNotEmpty()) {
                    val favoriteVacancies = data.vacancies.filter { it.isFavorite }
                    dataBase.favoritesDao.deleteAll()
                    favoriteVacancies.forEach() {
                        dataBase.favoritesDao.addFavoriteVacancy(VacancyEntityConverter.convert(it))
                    }
                }
            }

            404 -> {
                emit(NetworkResult.Error(ErrorType.NON_200_RESPONSE))
            }

            else -> {
                emit(NetworkResult.Error(ErrorType.SERVER_THROWABLE))
            }
        }
    }

    override suspend fun updateVacancy(vacancy: Vacancy) {
        if (vacancy.isFavorite) {
            dataBase.favoritesDao.addFavoriteVacancy(VacancyEntityConverter.convert(vacancy))
        } else {
            dataBase.favoritesDao.deleteFavoriteVacancy(VacancyEntityConverter.convert(vacancy))
        }
    }

    override fun getFavoriteVacanciesId(): Flow<List<String>> {
        return dataBase.favoritesDao.getAllVacancyIds()
    }
}