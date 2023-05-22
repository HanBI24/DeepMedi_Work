package com.example.deepmediwork.presentation.view.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.deepmediwork.presentation.viewmodel.MainScreenViewModel

@Composable
fun SetNavMainScreen(
    navController: NavHostController
) {
    RequestPermission()

    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
    val resultStateCode = mainScreenViewModel.stateCode.value.code

    MainScreen(navController, mainScreenViewModel, resultStateCode)
}
