package com.example.database

import android.content.Context
import androidx.room.Room
import com.example.database.impl.AppDatabaseImpl
import com.example.database.impl.dao.FavoritesDao

class AppDatabaseApi internal constructor(private val database: AppDatabaseImpl) {
    val favoritesDao: FavoritesDao
        get() = database.favoriteDao()
}

fun AppDatabaseApi(applicationContext: Context): AppDatabaseApi {
    val appDatabaseImpl =
        Room.databaseBuilder(applicationContext, AppDatabaseImpl::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .build()
    return AppDatabaseApi(appDatabaseImpl)
}