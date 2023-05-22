package com.example.deepmediwork.presentation.view.main

import android.Manifest
import android.content.Context
import android.os.Build
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.deepmediwork.navigation.NavScreen
import com.example.deepmediwork.presentation.viewmodel.MainScreenViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import kotlinx.coroutines.delay
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainScreen(
    navController: NavHostController
) {
    val permissionState = rememberMultiplePermissionsState(
        permissions = if (Build.VERSION.SDK_INT <= 28) {
            listOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        } else listOf(Manifest.permission.CAMERA)
    )

    if (!permissionState.allPermissionsGranted) {
        SideEffect {
            permissionState.launchMultiplePermissionRequest()
        }
    }

    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
    val resultStateCode = mainScreenViewModel.stateCode.value.code

    if (resultStateCode == 200) {
        LaunchedEffect(true) {
            delay(1500)
            mainScreenViewModel.resetState()
            navController.navigate(NavScreen.Result.route)
        }
    }

    val imageCapture = ImageCapture.Builder()
        .setTargetAspectRatio(AspectRatio.RATIO_4_3)
        .build()

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HomeTopAppBar()
        if (resultStateCode == 200) RecognizeFinishText()
        else RecognizeText()
        if (resultStateCode == 200) CameraAreaSuccess()
        else CameraArea(imageCapture, context, lifecycleOwner)
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
fun CameraArea(
    imageCapture: ImageCapture,
    context: Context,
    lifecycleOwner: LifecycleOwner
) {
    val previewView = remember { PreviewView(context) }

    ShowCameraPreview(context, lifecycleOwner, previewView, imageCapture)

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
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun CameraAreaSuccess() {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(100.dp),
                    imageVector = Icons.Default.Check,
                    contentDescription = "Success Icon",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
private fun ShowCameraPreview(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    previewView: PreviewView,
    imageCapture: ImageCapture
) {
    val cameraPreview = Preview.Builder().build()
    val cameraProvider = ProcessCameraProvider.getInstance(context).get()
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()

    cameraPreview.setSurfaceProvider(previewView.surfaceProvider)

    try {
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            cameraPreview,
            imageCapture
        )
    } catch (e: Exception) {
        println("onError Provider: $e")
    }
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

private fun takePhoto(
    context: Context,
    imageCapture: ImageCapture,
    mainScreenViewModel: MainScreenViewModel
) {
    val name = SimpleDateFormat(
        "yyyy-MM-dd-HH-mm-ss-SSS",
        Locale.KOREAN
    ).format(System.currentTimeMillis())
    val photoFile = File(
        context.cacheDir,
        "$name.jpeg"
    )
    val outputOptions = ImageCapture.OutputFileOptions
        .Builder(photoFile)
        .build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                println("onSuccess saved ${photoFile.name}")
                mainScreenViewModel.onUploadFaceImage(photoFile)
            }

            override fun onError(exception: ImageCaptureException) {
                println("onError Saved: $exception")
            }
        }
    )
}