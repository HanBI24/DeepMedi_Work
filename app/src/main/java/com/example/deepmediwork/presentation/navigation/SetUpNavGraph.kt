package com.example.deepmediwork.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.deepmediwork.navigation.NavScreen
import com.example.deepmediwork.presentation.view.main.MainScreen
import com.example.deepmediwork.presentation.view.result.ResultScreen

@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.Home.route
    ) {
        composable(route = NavScreen.Home.route) {
            MainScreen(navController)
        }
        composable(route = NavScreen.Result.route) {
            ResultScreen(navController)
        }
    }
}