package com.example.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_vacancies")
data class FavoriteVacancyEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "looking_number")
    val lookingNumber: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "address_town")
    val addressTown: String,
    @ColumnInfo(name = "address_street")
    val addressStreet: String,
    @ColumnInfo(name = "address_house")
    val addressHouse: String,
    @ColumnInfo(name = "company")
    val company: String,
    @ColumnInfo(name = "experience_preview_text")
    val experiencePreviewText: String,
    @ColumnInfo(name = "experience_text")
    val experienceText: String,
    @ColumnInfo(name = "published_date")
    val publishedDate: String,
    @ColumnInfo(name = "salary_short")
    val salaryShort: String,
    @ColumnInfo(name = "salary_full")
    val salaryFull: String,
    @ColumnInfo(name = "schedules")
    val schedules: String,
    @ColumnInfo(name = "applied_number")
    val appliedNumber: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "responsibilities")
    val responsibilities: String,
    @ColumnInfo(name = "questions")
    val questions: String,
)

