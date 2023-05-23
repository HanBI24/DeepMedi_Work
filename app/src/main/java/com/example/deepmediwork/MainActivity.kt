package com.example.deepmediwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.deepmediwork.presentation.navigation.SetUpNavGraph
import com.example.deepmediwork.ui.theme.DeepMediWorkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeepMediWorkTheme {
                // navigation 객체 생성 및 설정
                navController = rememberNavController()
                SetUpNavGraph(navController)
            }
        }
    }
}