package com.example.search.presentation.state

import com.example.common.utills.ErrorType
import com.example.search.domain.model.Jobs

interface SearchScreenState {

    object Loading : SearchScreenState

    data class Content(val jobs: Jobs) : SearchScreenState

    object JobsNotFound : SearchScreenState

    data class SearchError(val type: ErrorType) : SearchScreenState
}