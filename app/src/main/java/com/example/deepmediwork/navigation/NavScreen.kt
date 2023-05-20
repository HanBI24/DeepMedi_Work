package com.example.deepmediwork.navigation

sealed class NavScreen(val route: String) {
    object Home: NavScreen(route = "home")
    object Result: NavScreen(route = "result")
}