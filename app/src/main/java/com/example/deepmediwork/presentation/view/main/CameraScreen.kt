package com.example.deepmediwork.presentation.view.main

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.deepmediwork.presentation.viewmodel.MainScreenViewModel
import com.example.deepmediwork.ui.theme.DeepMediColor
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
    // 카메라 뷰 설정
    ShowCameraPreview(context, lifecycleOwner, previewView, imageCapture)

    Box(
        modifier = Modifier
            .padding(32.dp)
            .size(320.dp)
            .border(
                BorderStroke(
                    width = 3.dp,
                    color = DeepMediColor.Red
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
                    color = DeepMediColor.Red
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DeepMediColor.Green)
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
                    tint = DeepMediColor.White
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
    // 카메라 뷰를 띄우기 위한 설정
    val cameraPreview = Preview.Builder().build().also {
        // 프리뷰에 setSurfaceProvider() 전달
        // 이미지 데이터 받을 준비가 끝난 신호를 카메라에게 전달
        it.setSurfaceProvider(previewView.surfaceProvider)
    }
    // 카메라에 생명주기 binding
    val cameraProvider = ProcessCameraProvider.getInstance(context).get()
    // 후면 카메라 설정
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
        .build()

    // 생명주기 binding
    // 다시 binding 되기 전 모든 binding 해제
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
    // 이미지 파일 이름
    val name = SimpleDateFormat(
        "yyyy-MM-dd-HH-mm-ss-SSS",
        Locale.KOREAN
    ).format(System.currentTimeMillis())
    // 파일 생성
    val photoFile = File(
        context.cacheDir,   // 찍은 이미지를 임시 경로에 저장 (저장 x)
        "$name.jpeg"
    )
    // 이미지 저장하기 위한 옵션
    val outputOptions = ImageCapture.OutputFileOptions
        .Builder(photoFile)
        .build()

    // 사진 촬영
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                println("onSuccess saved ${photoFile.name}")
                // viewModel을 통해 서버로 이미지 전송
                mainScreenViewModel.onUploadFaceImage(photoFile)
            }

            override fun onError(exception: ImageCaptureException) {
                println("onError Saved: $exception")
            }
        }
    )
}