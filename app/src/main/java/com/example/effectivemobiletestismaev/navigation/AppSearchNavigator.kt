package com.example.effectivemobiletestismaev.navigation

import androidx.navigation.NavController
import com.example.effectivemobiletestismaev.R
import com.example.favorite.navigation.SearchNavigator

class AppSearchNavigator(
    private val navController: NavController
) : SearchNavigator {
    override fun openVacancyDetails() {
        navController.navigate(
            R.id.vacancyDetailFragment,
        )
    }
}