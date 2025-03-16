package com.example.effectivemobiletestismaev.di

import com.example.favorite.data.repository.FavoriteVacanciesRepositoryImpl
import com.example.favorite.domain.api.FavoriteVacanciesRepository
import com.example.favorite.domain.api.GetFavoriteVacanciesUseCase
import com.example.favorite.domain.api.UpdateFavoriteVacancyUseCase
import com.example.favorite.domain.impl.GetFavoriteVacanciesUseCaseImpl
import com.example.favorite.domain.impl.UpdateFavoriteVacancyUseCaseImpl
import com.example.favorite.presentation.viewmodel.FavoriteVacanciesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureFavoriteModule = module {

    viewModel {
        FavoriteVacanciesViewModel(
            getFavoriteVacanciesUseCase = get(),
            updateFavoriteVacancyUseCase = get(),
        )
    }

    single<GetFavoriteVacanciesUseCase> {
        GetFavoriteVacanciesUseCaseImpl(
            favoriteVacanciesRepository = get()
        )
    }

    single<UpdateFavoriteVacancyUseCase> {
        UpdateFavoriteVacancyUseCaseImpl(
            favoriteVacanciesRepository = get()
        )
    }

    single<FavoriteVacanciesRepository> {
        FavoriteVacanciesRepositoryImpl(
            dataBase = get(),
        )
    }
}