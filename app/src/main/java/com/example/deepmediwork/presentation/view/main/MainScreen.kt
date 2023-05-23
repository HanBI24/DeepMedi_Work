package com.example.deepmediwork.presentation.view.main

import android.content.Context
import androidx.camera.core.*
import androidx.camera.view.PreviewView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.deepmediwork.R
import com.example.deepmediwork.navigation.NavScreen
import com.example.deepmediwork.presentation.viewmodel.MainScreenViewModel
import com.example.deepmediwork.ui.theme.DeepMediColor
import kotlinx.coroutines.delay
import java.util.*

@OptIn(ExperimentalCoilApi::class)
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
        Image(
            rememberImagePainter(R.drawable.step_indicator),
            modifier = Modifier.size(350.dp),
            contentDescription = "Step Image Indicator"
        )
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
    Divider(color = DeepMediColor.Gray)
}

@Composable
fun RecognizeText() {
    Text(
        modifier = Modifier.padding(top = 40.dp),
        text = buildAnnotatedString {
            append("얼굴 인식을 위해\n")
            withStyle(
                style = SpanStyle(
                    color = DeepMediColor.Red
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
                    color = DeepMediColor.Red
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
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(color = DeepMediColor.Red, shape = CircleShape)
            .clickable {
                takePhoto(context, imageCapture, mainScreenViewModel)
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(color = DeepMediColor.Red, shape = CircleShape)
                .border(width = 4.dp, color = DeepMediColor.White, shape = CircleShape)
        )
    }
}

