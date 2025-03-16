package com.example.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyDto(
    @SerialName("id") val id: String,
    @SerialName("lookingNumber") val lookingNumber: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("address") val address: AddressDto? = null,
    @SerialName("company") val company: String? = null,
    @SerialName("experience") val experience: ExperienceDto? = null,
    @SerialName("publishedDate") val publishedDate: String? = null,
    @SerialName("isFavorite") val isFavorite: Boolean? = null,
    @SerialName("salary") val salary: SalaryDto? = null,
    @SerialName("schedules") val schedules: List<String>? = null,
    @SerialName("appliedNumber") val appliedNumber: Int? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("responsibilities") val responsibilities: String? = null,
    @SerialName("questions") val questions: List<String>? = null,
)

@Serializable
data class AddressDto(
    @SerialName("town") val town: String? = null,
    @SerialName("street") val street: String? = null,
    @SerialName("house") val house: String? = null,
)

@Serializable
data class ExperienceDto(
    @SerialName("previewText") val previewText: String? = null,
    @SerialName("text") val text: String? = null,
)

@Serializable
data class SalaryDto(
    @SerialName("short") val short: String? = null,
    @SerialName("full") val full: String? = null,
)