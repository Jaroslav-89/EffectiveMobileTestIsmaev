package com.example.network

import com.example.network.dto.Response
import com.example.network.utills.NetworkConnectionCheckerApi

interface NetworkClient {
    suspend fun doRequest(): Response
}

private class NetworkClientApi(
    private val networkClientImpl: RetrofitNetworkClient
) : NetworkClient {
    override suspend fun doRequest(): Response {
        return networkClientImpl.doRequest()
    }
}

fun NetworkClientApi(
    jobsApiService: JobsApiService,
    connectionChecker: NetworkConnectionCheckerApi,
): NetworkClient {
    val networkClientImpl =
        RetrofitNetworkClient(jobsApiService, connectionChecker)
    return NetworkClientApi(networkClientImpl)
}