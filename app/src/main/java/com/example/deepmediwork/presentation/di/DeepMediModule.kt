package com.example.deepmediwork.presentation.di

import com.example.deepmediwork.common.Constants
import com.example.deepmediwork.data.remote.api.DeepMediApi
import com.example.deepmediwork.data.repository.upload.UploadFaceImageRepositoryImpl
import com.example.deepmediwork.data.repository.user_info.UserInfoRepositoryImpl
import com.example.deepmediwork.domain.remote.repository.upload.UploadFaceImageRepository
import com.example.deepmediwork.domain.remote.repository.user_info.UserInfoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object DeepMediModule {

    // API 의존성 주입
    @Provides
    @Singleton
    fun provideDeepMediApi(): DeepMediApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeepMediApi::class.java)
    }

    // UploadFaceImageRepository 의존성 주입
    @Provides
    @Singleton
    fun provideUploadFaceImageRepository(
        deepMediApi: DeepMediApi
    ): UploadFaceImageRepository {
        return UploadFaceImageRepositoryImpl(deepMediApi)
    }

    // UserInfoRepository 의존성 주입
    @Provides
    @Singleton
    fun provideUserInfoRepository(
        deepMediApi: DeepMediApi
    ): UserInfoRepository {
        return UserInfoRepositoryImpl(deepMediApi)
    }
}