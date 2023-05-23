package com.example.deepmediwork.data.repository.user_info

import com.example.deepmediwork.data.remote.api.DeepMediApi
import com.example.deepmediwork.data.mapper.Mapper.toUserInfoItem
import com.example.deepmediwork.domain.remote.model.user_info.UserInfoItem
import com.example.deepmediwork.domain.remote.repository.user_info.UserInfoRepository
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val deepMediApi: DeepMediApi
) : UserInfoRepository {

    // 유저 데이터 반환
    override suspend fun getUserInfo(): UserInfoItem {
        return deepMediApi.getUserInfo().toUserInfoItem()
    }
}