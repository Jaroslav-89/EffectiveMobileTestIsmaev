package com.example.database

import com.example.database.impl.AppDatabaseImpl
import com.example.database.impl.dao.FavoritesDao

class AppDatabaseApi internal constructor(private val database: AppDatabaseImpl) {
    val favoritesDao: FavoritesDao
        get() = database.favoriteDao()
}