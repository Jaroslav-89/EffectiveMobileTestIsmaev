package com.example.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyDto(
    @SerialName("id") val id: String,
    @SerialName("lookingNumber") val lookingNumber: Int?,
    @SerialName("title") val title: String?,
    @SerialName("address") val address: AddressDto?,
    @SerialName("company") val company: String?,
    @SerialName("experience") val experience: ExperienceDto?,
    @SerialName("publishedDate") val publishedDate: String?,
    @SerialName("isFavorite") val isFavorite: Boolean?,
    @SerialName("salary") val salary: SalaryDto?,
    @SerialName("schedules") val schedules: List<String>?,
    @SerialName("appliedNumber") val appliedNumber: Int?,
    @SerialName("description") val description: String?,
    @SerialName("responsibilities") val responsibilities: String?,
    @SerialName("questions") val questions: List<String>?,
)

@Serializable
data class AddressDto(
    @SerialName("town") val town: String?,
    @SerialName("street") val street: String?,
    @SerialName("house") val house: String?,
)

@Serializable
data class ExperienceDto(
    @SerialName("previewText") val previewText: String?,
    @SerialName("text") val text: String?,
)

@Serializable
data class SalaryDto(
    @SerialName("short") val short: String?,
    @SerialName("full") val full: String?,
)