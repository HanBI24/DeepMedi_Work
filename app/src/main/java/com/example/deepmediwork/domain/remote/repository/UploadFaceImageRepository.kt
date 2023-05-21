package com.example.deepmediwork.domain.remote.repository

import com.example.deepmediwork.domain.remote.model.UploadFaceImageItem
import java.io.File

interface UploadFaceImageRepository {

    suspend fun uploadFaceImage(file: File): UploadFaceImageItem
}