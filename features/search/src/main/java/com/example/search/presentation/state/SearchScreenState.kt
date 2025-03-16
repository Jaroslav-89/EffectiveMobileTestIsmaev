package com.example.search.presentation.state

import com.example.common.utills.ErrorType
import com.example.search.domain.model.Jobs
import com.example.search.domain.model.Offer

interface SearchScreenState {

    object Loading : SearchScreenState

    data class Content(val jobs: Jobs) : SearchScreenState

    data class JobsNotFound(val offers: List<Offer>) : SearchScreenState

    data class SearchError(val type: ErrorType) : SearchScreenState
}