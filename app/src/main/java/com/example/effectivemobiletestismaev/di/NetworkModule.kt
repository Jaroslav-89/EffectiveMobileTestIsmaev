package com.example.effectivemobiletestismaev.di

import com.example.effectivemobiletestismaev.BuildConfig
import com.example.network.JobsApiService
import com.example.network.NetworkClient
import com.example.network.NetworkConnectionChecker
import com.example.network.RetrofitNetworkClient
import com.example.network_utills.AndroidNetworkConnectionChecker
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkModule = module {

    single<NetworkConnectionChecker> { AndroidNetworkConnectionChecker(context = androidContext()) }

    single<JobsApiService> { JobsApiService(baseUrl = BuildConfig.JOBS_API_BASE_URL) }

    single<NetworkClient> {
        RetrofitNetworkClient(
            jobsApiService = get(),
            connectionChecker = get()
        )
    }
}