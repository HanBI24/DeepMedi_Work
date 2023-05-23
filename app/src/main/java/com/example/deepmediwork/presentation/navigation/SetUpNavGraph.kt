package com.example.deepmediwork.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.deepmediwork.navigation.NavScreen
import com.example.deepmediwork.presentation.view.main.MainScreen
import com.example.deepmediwork.presentation.view.main.SetNavMainScreen
import com.example.deepmediwork.presentation.view.result.ResultScreen
import com.example.deepmediwork.presentation.view.result.SetNavResultScreen

// navigation을 통한 화면 전환 설정
@Composable
fun SetUpNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.Home.route     // 초기 화면 설정
    ) {
        composable(route = NavScreen.Home.route) {
            SetNavMainScreen(navController)
        }
        composable(route = NavScreen.Result.route) {
            SetNavResultScreen(navController)
        }
    }
}