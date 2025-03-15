package com.example.search.domain.model

import com.example.common.domain.model.Vacancy

data class Jobs(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>,
)