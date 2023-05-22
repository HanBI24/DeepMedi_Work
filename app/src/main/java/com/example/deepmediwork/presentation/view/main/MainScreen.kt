package com.example.deepmediwork.presentation.view.main

import android.content.Context
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.example.deepmediwork.navigation.NavScreen
import com.example.deepmediwork.presentation.viewmodel.MainScreenViewModel
import kotlinx.coroutines.delay
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MainScreen(
    navController: NavHostController,
    mainScreenViewModel: MainScreenViewModel,
    resultStateCode: Int
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    val imageCapture = ImageCapture.Builder()
        .setTargetAspectRatio(AspectRatio.RATIO_4_3)
        .build()
    val previewView = remember { PreviewView(context) }

    if (resultStateCode == 200) {
        LaunchedEffect(true) {
            delay(1500)
            mainScreenViewModel.resetState()
            navController.navigate(NavScreen.Result.route)
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HomeTopAppBar()
        if (resultStateCode == 200) RecognizeFinishText()
        else RecognizeText()
        if (resultStateCode == 200) CameraAreaSuccess()
        else CameraArea(context, lifecycleOwner, imageCapture, previewView)
        ShotButton(imageCapture, mainScreenViewModel, context)
    }
}

@Composable
fun HomeTopAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp, bottom = 20.dp),
            text = "홈",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
    Divider(color = Color(0xFFD8D8D8))
}

@Composable
fun RecognizeText() {
    Text(
        modifier = Modifier.padding(top = 40.dp),
        text = buildAnnotatedString {
            append("얼굴 인식을 위해\n")
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFD03843)
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
                    color = Color(0xFFD03843)
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
fun ShotButton(
    imageCapture: ImageCapture,
    mainScreenViewModel: MainScreenViewModel,
    context: Context
) {
    Button(
        onClick = {
            takePhoto(context, imageCapture, mainScreenViewModel)
        },
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Text(text = "사진 촬영")
    }
}

