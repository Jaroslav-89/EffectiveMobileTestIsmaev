package com.example.database.di

import androidx.room.Room
import com.example.database.AppDatabaseApi
import com.example.database.impl.AppDatabaseImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabaseApi> {
        val appDatabaseImpl =
            Room.databaseBuilder(androidContext(), AppDatabaseImpl::class.java, "database.db")
                .fallbackToDestructiveMigration()
                .build()
        AppDatabaseApi(appDatabaseImpl)
    }
}