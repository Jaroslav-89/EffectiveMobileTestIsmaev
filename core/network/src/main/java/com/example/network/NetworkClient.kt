package com.example.network

import com.example.network.dto.Response

interface NetworkClient {
    suspend fun doRequest(): Response
}