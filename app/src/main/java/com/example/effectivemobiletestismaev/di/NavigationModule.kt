package com.example.effectivemobiletestismaev.di

import androidx.navigation.NavController
import com.example.effectivemobiletestismaev.navigation.AppFavoriteNavigator
import com.example.effectivemobiletestismaev.navigation.AppSearchNavigator
import com.example.favorite.navigation.FavoriteNavigator
import com.example.favorite.navigation.SearchNavigator
import org.koin.dsl.module

fun navigationModule() = module {
    single<SearchNavigator> { (navController: NavController) ->
        AppSearchNavigator(navController)
    }

    single<FavoriteNavigator> { (navController: NavController) ->
        AppFavoriteNavigator(navController)
    }
}