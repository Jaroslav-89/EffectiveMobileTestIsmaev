package com.example.search.data.converters

import com.example.common.domain.model.Vacancy
import com.example.database.entity.FavoriteVacancyEntity

object VacancyEntityConverter {
    fun convert(vacancy: Vacancy): FavoriteVacancyEntity {
        return FavoriteVacancyEntity(
            id = vacancy.id,
            lookingNumber = vacancy.lookingNumber,
            title = vacancy.title,
            addressTown = vacancy.addressTown,
            addressStreet = vacancy.addressStreet,
            addressHouse = vacancy.addressHouse,
            company = vacancy.company,
            experiencePreviewText = vacancy.experiencePreviewText,
            experienceText = vacancy.experienceText,
            publishedDate = vacancy.publishedDate,
            salaryShort = vacancy.salaryShort,
            salaryFull = vacancy.salaryFull,
            schedules = vacancy.schedules.joinToString(","),
            appliedNumber = vacancy.appliedNumber,
            description = vacancy.description,
            responsibilities = vacancy.responsibilities,
            questions = vacancy.questions.joinToString(","),
        )
    }

    fun convert(favoriteVacancyEntity: FavoriteVacancyEntity): Vacancy {
        return Vacancy(
            id = favoriteVacancyEntity.id,
            lookingNumber = favoriteVacancyEntity.lookingNumber,
            title = favoriteVacancyEntity.title,
            addressTown = favoriteVacancyEntity.addressTown,
            addressStreet = favoriteVacancyEntity.addressStreet,
            addressHouse = favoriteVacancyEntity.addressHouse,
            company = favoriteVacancyEntity.company,
            experiencePreviewText = favoriteVacancyEntity.experiencePreviewText,
            experienceText = favoriteVacancyEntity.experienceText,
            publishedDate = favoriteVacancyEntity.publishedDate,
            isFavorite = true,
            salaryShort = favoriteVacancyEntity.salaryShort,
            salaryFull = favoriteVacancyEntity.salaryFull,
            schedules = mapStringToListString(favoriteVacancyEntity.schedules),
            appliedNumber = favoriteVacancyEntity.appliedNumber,
            description = favoriteVacancyEntity.description,
            responsibilities = favoriteVacancyEntity.responsibilities,
            questions = mapStringToListString(favoriteVacancyEntity.questions),
        )
    }

    private fun mapStringToListString(str: String): List<String> {
        return if (str.isEmpty()) {
            emptyList()
        } else {
            str.split(",")
        }
    }
}
