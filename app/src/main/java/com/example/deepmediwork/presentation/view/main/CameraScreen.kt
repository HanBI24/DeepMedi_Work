package com.example.deepmediwork.presentation.view.main

import android.content.Context
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.deepmediwork.presentation.viewmodel.MainScreenViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CameraArea(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    imageCapture: ImageCapture,
    previewView: PreviewView
) {
    ShowCameraPreview(context, lifecycleOwner, previewView, imageCapture)

    Box(
        modifier = Modifier
            .padding(32.dp)
            .size(320.dp)
            .border(
                BorderStroke(
                    width = 3.dp,
                    color = Color(0xFFD03843)
                )
            )
    ) {
        AndroidView(
            factory = {
                previewView
            },
            modifier = Modifier.fillMaxSize()
        ) {

        }
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
                    color = Color(0xFFD03843)
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFD9FFEC))
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

    cameraProvider.unbindAll()
    cameraProvider.bindToLifecycle(
        lifecycleOwner,
        cameraSelector,
        cameraPreview,
        imageCapture
    )
}

fun takePhoto(
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