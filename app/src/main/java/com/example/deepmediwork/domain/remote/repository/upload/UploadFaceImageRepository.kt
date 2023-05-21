package com.example.deepmediwork.domain.remote.repository.upload

import com.example.deepmediwork.domain.remote.model.upload.UploadFaceImageItem
import java.io.File

interface UploadFaceImageRepository {

    suspend fun uploadFaceImage(file: File): UploadFaceImageItem
}