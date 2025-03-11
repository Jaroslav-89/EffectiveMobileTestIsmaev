package com.example.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_vacancies")
data class FavoriteVacancyEntity (
    @PrimaryKey(autoGenerate = false)
    val id: String,
    @ColumnInfo("looking_number")
    val lookingNumber: Int,
    val title: String,
    @ColumnInfo("address_town")
    val addressTown: String,
    @ColumnInfo("address_street")
    val addressStreet: String,
    @ColumnInfo("address_house")
    val addressHouse: String,
    val company: String,
    @ColumnInfo("experience_preview_text")
    val experiencePreviewText: String,
    @ColumnInfo("experience_text")
    val experienceText: String,
    @ColumnInfo("published_date")
    val publishedDate: String,
    val isFavorite: Boolean,
    @ColumnInfo("salary_short")
    val salaryShort: String,
    @ColumnInfo("salary_full")
    val salaryFull: String,
    val schedules: String,
    @ColumnInfo("applied_number")
    val appliedNumber: Int,
    val description: String,
    val responsibilities: String,
    val questions: String,
)

