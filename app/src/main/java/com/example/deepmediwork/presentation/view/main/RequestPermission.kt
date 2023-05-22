package com.example.deepmediwork.presentation.view.main

import android.Manifest
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission() {
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
}