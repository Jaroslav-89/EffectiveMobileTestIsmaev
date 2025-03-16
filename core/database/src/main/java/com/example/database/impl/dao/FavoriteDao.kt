package com.example.database.impl.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.entity.FavoriteVacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteVacancy(favorite: FavoriteVacancyEntity)

    @Delete
    suspend fun deleteFavoriteVacancy(favorite: FavoriteVacancyEntity)

    @Query("DELETE FROM favorites_vacancies")
    suspend fun deleteAll()

    @Query("SELECT id FROM favorites_vacancies")
    fun getAllVacancyIds(): Flow<List<String>>

    @Query("SELECT * FROM favorites_vacancies")
    fun getAllVacancy(): Flow<List<FavoriteVacancyEntity>>

    @Query("SELECT COUNT(*) FROM favorites_vacancies")
    fun getFavoritesCount(): Flow<Int>
}