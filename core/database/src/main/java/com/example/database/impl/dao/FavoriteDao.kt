package com.example.database.impl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.FavoriteVacancyEntity
import com.example.database.entity.FavoriteVacancyIdEntity

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteVacancy(favorite: FavoriteVacancyEntity)

    @Delete
    suspend fun deleteFavoriteVacancy(favorite: FavoriteVacancyEntity)

    @Query("SELECT id FROM favorites_vacancies")
    suspend fun getAllVacancyIds(): List<FavoriteVacancyIdEntity>
}