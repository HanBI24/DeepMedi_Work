package com.example.deepmediwork.presentation.view.result

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ResultScreen(
    navController: NavHostController
) {
    Column {
        ResultTopAppBar(
            navController = navController
        )
    }
}

@Composable
fun ResultTopAppBar(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(top = 36.dp, start = 16.dp, bottom = 20.dp)
                .align(Alignment.CenterStart),
        ) {
            Text(
                text = "다시 측정",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Red
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 36.dp, bottom = 20.dp)
                .align(Alignment.Center),
            text = "측정 결과",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
    Divider(color = Color.LightGray)
}