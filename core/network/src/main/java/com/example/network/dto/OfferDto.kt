package com.example.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OfferDto(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("button") val button: ButtonDto? = null,
)

@Serializable
data class ButtonDto(
    @SerialName("text") val text: String? = null,
)