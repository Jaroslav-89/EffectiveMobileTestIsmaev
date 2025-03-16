package com.example.network.dto

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class JobsResponse(
    @SerialName("offers") val offers: List<OfferDto>?,
    @SerialName("vacancies") val vacancies: List<VacancyDto>?,
) : Response()
