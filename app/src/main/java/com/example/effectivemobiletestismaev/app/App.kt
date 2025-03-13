package com.example.effectivemobiletestismaev.app

import android.app.Application
import com.example.effectivemobiletestismaev.di.appModule
import com.example.effectivemobiletestismaev.di.databaseModule
import com.example.effectivemobiletestismaev.di.featureFavoriteModule
import com.example.effectivemobiletestismaev.di.featureSearchModule
import com.example.effectivemobiletestismaev.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                databaseModule,
                networkModule,
                featureSearchModule,
                featureFavoriteModule,
            )
        }
    }
}