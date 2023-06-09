package com.example.deepmediwork.data.mapper

import com.example.deepmediwork.data.remote.dto.upload.UploadFaceImageDto
import com.example.deepmediwork.data.remote.dto.user_info.UserInfoDto
import com.example.deepmediwork.domain.remote.model.upload.UploadFaceImageItem
import com.example.deepmediwork.domain.remote.model.user_info.UserInfoItem

// 클린 아키텍처를 위한 Mapper
object Mapper {

    fun UploadFaceImageDto.toUploadFaceImageItem(): UploadFaceImageItem {
        return UploadFaceImageItem(
            code = code,
            message = message
        )
    }

    fun UserInfoDto.toUserInfoItem(): UserInfoItem {
        return UserInfoItem(
            alcohol = alcohol,
            bpm = bpm,
            cumulant_minus_point = cumulant_minus_point,
            dia = dia,
            fatigue = fatigue,
            name = name,
            profile = profile,
            resp = resp,
            spo2 = spo2,
            stress = stress,
            sys = sys,
            temp = temp
        )
    }
}