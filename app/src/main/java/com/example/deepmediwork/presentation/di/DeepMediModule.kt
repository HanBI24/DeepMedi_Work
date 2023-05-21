package com.example.deepmediwork.presentation.di

import com.example.deepmediwork.common.Constants
import com.example.deepmediwork.data.remote.api.DeepMediApi
import com.example.deepmediwork.data.repository.UploadFaceImageRepositoryImpl
import com.example.deepmediwork.domain.remote.repository.UploadFaceImageRepository
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

    @Provides
    @Singleton
    fun provideDeepMediApi(): DeepMediApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DeepMediApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUploadFaceImageRepository(
        deepMediApi: DeepMediApi
    ): UploadFaceImageRepository {
        return UploadFaceImageRepositoryImpl(deepMediApi)
    }
}