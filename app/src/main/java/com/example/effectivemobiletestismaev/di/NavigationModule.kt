package com.example.effectivemobiletestismaev.di

import androidx.navigation.NavController
import com.example.effectivemobiletestismaev.navigation.AppFavoriteNavigator
import com.example.effectivemobiletestismaev.navigation.AppSearchNavigator
import com.example.favorite.navigation.FavoriteNavigator
import com.example.favorite.navigation.SearchNavigator
import org.koin.dsl.module

fun navigationModule() = module {
    factory<SearchNavigator> { (navController: NavController) ->
        AppSearchNavigator(navController)
    }

    factory<FavoriteNavigator> { (navController: NavController) ->
        AppFavoriteNavigator(navController)
    }
}