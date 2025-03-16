package com.example.effectivemobiletestismaev.di

import com.example.effectivemobiletestismaev.data.GetFavoriteVacancyCountRepositoryImpl
import com.example.effectivemobiletestismaev.domain.api.GetFavoriteVacancyCountRepository
import com.example.effectivemobiletestismaev.domain.api.GetFavoriteVacancyCountUseCase
import com.example.effectivemobiletestismaev.domain.impl.GetFavoriteVacancyCountUseCaseImpl
import com.example.effectivemobiletestismaev.presentation.viewmodel.RootViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        RootViewModel(
            useCase = get()
        )
    }

    single<GetFavoriteVacancyCountUseCase> {
        GetFavoriteVacancyCountUseCaseImpl(repository = get())
    }

    single<GetFavoriteVacancyCountRepository> {
        GetFavoriteVacancyCountRepositoryImpl(database = get())
    }
}