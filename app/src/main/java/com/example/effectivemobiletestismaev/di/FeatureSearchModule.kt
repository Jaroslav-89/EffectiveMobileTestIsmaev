package com.example.effectivemobiletestismaev.di

import com.example.search.data.repository.SearchRepositoryImpl
import com.example.search.domain.api.SearchRepository
import com.example.search.domain.api.SearchUseCase
import com.example.search.domain.api.UpdateVacancyUseCase
import com.example.search.domain.impl.SearchUseCaseImpl
import com.example.search.domain.impl.UpdateVacancyUseCaseImpl
import com.example.search.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureSearchModule = module {
    viewModel {
        SearchViewModel(
            searchUseCase = get(),
            updateVacancyUseCase = get()
        )
    }

    single<SearchUseCase> { SearchUseCaseImpl(searchRepository = get()) }

    single<UpdateVacancyUseCase> { UpdateVacancyUseCaseImpl(searchRepository = get()) }

    single<SearchRepository> {
        SearchRepositoryImpl(
            networkClient = get(),
            dataBase = get(),
        )
    }
}