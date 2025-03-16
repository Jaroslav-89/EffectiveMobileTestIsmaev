package com.example.effectivemobiletestismaev.di

import com.example.effectivemobiletestismaev.BuildConfig
import com.example.network.JobsApiService
import com.example.network.NetworkClient
import com.example.network.NetworkClientApi
import com.example.network.utills.NetworkConnectionCheckerApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {

    single<NetworkConnectionCheckerApi> {
        NetworkConnectionCheckerApi(applicationContext = androidContext())
    }

    single<JobsApiService> { JobsApiService(baseUrl = BuildConfig.JOBS_API_BASE_URL) }

    single<NetworkClient> {
        NetworkClientApi(
            jobsApiService = get(),
            connectionChecker = get()
        )
    }
}