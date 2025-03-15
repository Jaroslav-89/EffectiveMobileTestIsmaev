package com.example.network

import com.example.network.dto.JobsResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.http.GET

interface JobsApiService {
    @GET("/u/0/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download/")
    suspend fun getOffersAndVacancies(): JobsResponse
}

fun JobsApiService(baseUrl: String): JobsApiService {
    val retrofit = retrofit(baseUrl)
    return retrofit.create(JobsApiService::class.java)
}

private fun retrofit(baseUrl: String): Retrofit {
    val jsonConverterFactory = Json.asConverterFactory(MediaType.get("application/json"))
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .build()
}