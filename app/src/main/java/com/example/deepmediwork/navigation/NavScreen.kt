package com.example.deepmediwork.navigation

sealed class NavScreen(val route: String) {
    object Home: NavScreen(route = "home")      // home 화면
    object Result: NavScreen(route = "result")  // result 화면
}