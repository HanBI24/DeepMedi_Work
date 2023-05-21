package com.example.deepmediwork.domain.remote.repository.user_info

import com.example.deepmediwork.domain.remote.model.user_info.UserInfoItem

interface UserInfoRepository {

    suspend fun getUserInfo(): UserInfoItem
}