package com.example.deepmediwork.data.remote.api

import com.example.deepmediwork.data.remote.dto.upload.UploadFaceImageDto
import com.example.deepmediwork.data.remote.dto.user_info.UserInfoDto
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DeepMediApi {

    @Multipart
    @POST("/deepmedi-test-first")
    suspend fun uploadFaceImage(
        @Part file: MultipartBody.Part
    ): UploadFaceImageDto

    @GET("/deepmedi-test-second")
    suspend fun getUserInfo(): UserInfoDto
}