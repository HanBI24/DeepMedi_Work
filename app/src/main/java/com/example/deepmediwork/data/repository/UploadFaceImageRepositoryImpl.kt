package com.example.deepmediwork.data.repository

import com.example.deepmediwork.data.remote.api.DeepMediApi
import com.example.deepmediwork.data.remote.dto.UploadFaceImageDto
import com.example.deepmediwork.data.remote.mapper.Mapper.toUploadFaceImageItem
import com.example.deepmediwork.domain.remote.model.UploadFaceImageItem
import com.example.deepmediwork.domain.remote.repository.UploadFaceImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class UploadFaceImageRepositoryImpl @Inject constructor(
    private val deepMediApi: DeepMediApi
) : UploadFaceImageRepository {

    override suspend fun uploadFaceImage(file: File): UploadFaceImageItem {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                deepMediApi.uploadFaceImage(
                    MultipartBody.Part.createFormData(
                        "file",
                        file.name,
                        file.asRequestBody()
                    )
                ).toUploadFaceImageItem()
            } catch (e: Exception) {
                UploadFaceImageDto(-1, e.toString()).toUploadFaceImageItem()
            }
        }
    }
}