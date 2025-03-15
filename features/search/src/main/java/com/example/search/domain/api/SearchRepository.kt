package com.example.search.domain.api

import com.example.common.utills.NetworkResult
import com.example.search.domain.model.Jobs
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getJobs(): Flow<NetworkResult<Jobs>>
}