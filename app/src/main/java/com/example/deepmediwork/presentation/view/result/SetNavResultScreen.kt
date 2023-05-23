package com.example.deepmediwork.presentation.view.result

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.deepmediwork.presentation.viewmodel.ResultScreenViewModel

@Composable
fun SetNavResultScreen(
    navController: NavHostController
) {
    // state 가져옴
    val resultScreenViewModel: ResultScreenViewModel = hiltViewModel()
    val userInfo = resultScreenViewModel.userInfoState.value

    // 결과 화면 출력
    ResultScreen(navController = navController, userInfo = userInfo)
}