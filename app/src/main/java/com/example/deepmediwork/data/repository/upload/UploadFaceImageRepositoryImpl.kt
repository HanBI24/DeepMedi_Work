package com.example.deepmediwork.data.repository.upload

import com.example.deepmediwork.data.remote.api.DeepMediApi
import com.example.deepmediwork.data.remote.dto.upload.UploadFaceImageDto
import com.example.deepmediwork.data.mapper.Mapper.toUploadFaceImageItem
import com.example.deepmediwork.domain.remote.model.upload.UploadFaceImageItem
import com.example.deepmediwork.domain.remote.repository.upload.UploadFaceImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UploadFaceImageRepositoryImpl @Inject constructor(
    private val deepMediApi: DeepMediApi
) : UploadFaceImageRepository {

    override suspend fun uploadFaceImage(file: File): UploadFaceImageItem {
        return deepMediApi.uploadFaceImage(
            MultipartBody.Part.createFormData(
                "file",
                file.name,
                file.asRequestBody()
            )
        ).toUploadFaceImageItem()

    }
}