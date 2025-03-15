package com.example.database.impl

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.entity.FavoriteVacancyEntity
import com.example.database.impl.dao.FavoritesDao

@Database(
    version = 3,
    entities = [FavoriteVacancyEntity::class],
    exportSchema = false,
)
internal abstract class AppDatabaseImpl : RoomDatabase() {

    abstract fun favoriteDao(): FavoritesDao
}