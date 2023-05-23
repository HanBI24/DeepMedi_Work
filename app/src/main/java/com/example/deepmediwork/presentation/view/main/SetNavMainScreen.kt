package com.example.deepmediwork.presentation.view.main

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.deepmediwork.presentation.viewmodel.MainScreenViewModel

@Composable
fun SetNavMainScreen(
    navController: NavHostController
) {
    // 권한 설정
    RequestPermission()

    // state 가져옴
    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
    val resultStateCode = mainScreenViewModel.stateCode.value.code

    // 메인 화면 출력
    MainScreen(navController, mainScreenViewModel, resultStateCode)
}
