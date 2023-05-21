package com.example.deepmediwork.presentation.view.main

import android.Manifest
import android.app.Application
import android.content.Context
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCapture.FLASH_MODE_ON
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
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
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.example.deepmediwork.navigation.NavScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    )

    if(!permissionState.allPermissionsGranted) {
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HomeTopAppBar()
        RecognizeText()
        CameraArea()
        ShotButton(navController)
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
                .padding(top = 36.dp, start = 32.dp, bottom = 20.dp),
            text = "홈",
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
    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

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
        AndroidView(
            factory = {
                val previewView = PreviewView(it)
                showCameraPreview(context, lifecycleOwner, previewView)
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun showCameraPreview(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView
) {
    val cameraPreview = Preview.Builder().build()
    val cameraProvider = ProcessCameraProvider.getInstance(context).get()
    val imageAnalysis = ImageAnalysis.Builder()
        .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
        .build()
    val imageCapture = ImageCapture.Builder()
        .setFlashMode(FLASH_MODE_ON)
        .setTargetAspectRatio(AspectRatio.RATIO_4_3)
        .build()
    val imageSelector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()

    cameraPreview.setSurfaceProvider(previewView.surfaceProvider)
    try {
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            imageSelector,
            cameraPreview,
            imageAnalysis,
            imageCapture
        )
    } catch(e: Exception) {
        e.printStackTrace()
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