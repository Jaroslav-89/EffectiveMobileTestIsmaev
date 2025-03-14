package com.example.network

import com.example.network.dto.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class RetrofitNetworkClient(
    private val jobsApiService: JobsApiService,
    private val connectionChecker: NetworkConnectionChecker,
) : NetworkClient {
    override suspend fun doRequest(): Response {
        if (!connectionChecker.isConnected()) return Response().apply { resultCode = -1 }

        return withContext(Dispatchers.IO) {
            try {
                val response = jobsApiService.getOffersAndVacancies()
                response.apply { resultCode = 200 }
            } catch (e1: IOException) {
                Response().apply { resultCode = 500 }
            } catch (e2: HttpException) {
                Response().apply { resultCode = 404 }
            }
        }
    }
}