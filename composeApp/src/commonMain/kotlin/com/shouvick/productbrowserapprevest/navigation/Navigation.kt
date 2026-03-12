package com.shouvick.productbrowserapprevest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.shouvick.productbrowserapprevest.feature.product.ui.home.HomeScreen
import com.shouvick.productbrowserapprevest.feature.product.ui.productDetails.ProductDetailsScreen

@Composable
fun Navigation() {
    val navController  = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ){
        composable <Routes.Home>{
            HomeScreen(
                onCardClick = {
                    navController.navigate(Routes.Details(it))
                }
            )
        }
        composable <Routes.Details>{
            val id = it.toRoute<Routes.Details>().id

            ProductDetailsScreen(
                navigateBack = {navController.navigateUp()}
            )
        }
    }
}