package com.example.deepmediwork.presentation.view.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deepmediwork.navigation.NavScreen

@Composable
fun MainScreen(
    navController: NavHostController
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HomeTopAppBar(title = "홈")
        RecognizeText()
        CameraArea()
        ShotButton(navController)
    }
}

@Composable
fun HomeTopAppBar(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(top = 36.dp, start = 32.dp, bottom = 20.dp),
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
    Divider(color = Color.LightGray)
}

@Composable
fun RecognizeText() {
    Text(
        modifier = Modifier.padding(top = 40.dp),
        text = buildAnnotatedString {
            append("얼굴 인식을 위해\n")
            withStyle(
                style = SpanStyle(
                    color = Color.Red
                )
            ) {
                append("화면을 응시")
            }
            append("해 주세요.")
        },
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    )
}

@Composable
fun RecognizeFinishText() {
    Text(
        modifier = Modifier.padding(top = 40.dp),
        text = buildAnnotatedString {
            append("얼굴 인식 ")
            withStyle(
                style = SpanStyle(
                    color = Color.Red
                )
            ) {
                append("성공")
            }
        },
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    )
}

@Composable
fun CameraArea() {
    Box(
        modifier = Modifier
            .padding(32.dp)
            .size(320.dp)
            .border(
                BorderStroke(
                    width = 3.dp,
                    color = Color.Red
                )
            )
    ) {

    }
}

@Composable
fun ShotButton(
    navController: NavHostController
) {
    Button(
        onClick = {
            navController.navigate(NavScreen.Result.route)
        },
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Text(text = "사진 촬영")
    }
}