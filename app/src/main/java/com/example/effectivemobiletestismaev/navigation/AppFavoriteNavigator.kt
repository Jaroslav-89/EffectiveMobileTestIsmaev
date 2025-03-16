package com.example.effectivemobiletestismaev.navigation

import androidx.navigation.NavController
import com.example.effectivemobiletestismaev.R
import com.example.favorite.navigation.FavoriteNavigator

class AppFavoriteNavigator(
    private val navController: NavController
) : FavoriteNavigator {
    override fun openVacancyDetails() {
        navController.navigate(
            R.id.vacancyDetailFragment,
        )
    }
}