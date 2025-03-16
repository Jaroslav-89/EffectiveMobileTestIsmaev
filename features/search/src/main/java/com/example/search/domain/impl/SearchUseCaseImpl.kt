package com.example.search.domain.impl

import com.example.common.utills.NetworkResult
import com.example.search.domain.api.SearchRepository
import com.example.search.domain.api.SearchUseCase
import com.example.search.domain.model.Jobs
import kotlinx.coroutines.flow.Flow

class SearchUseCaseImpl(private val searchRepository: SearchRepository) : SearchUseCase {

    override fun getJobs(): Flow<NetworkResult<Jobs>> {
        return searchRepository.getJobs()
    }
}