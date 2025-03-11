package com.example.effectivemobiletestismaev.di

import com.example.database.AppDatabaseApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<AppDatabaseApi> {
        AppDatabaseApi(applicationContext = androidContext())
    }
}