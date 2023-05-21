package com.example.deepmediwork.data.remote.mapper

import com.example.deepmediwork.data.remote.dto.UploadFaceImageDto
import com.example.deepmediwork.domain.remote.model.UploadFaceImageItem

object Mapper {

    fun UploadFaceImageDto.toUploadFaceImageItem(): UploadFaceImageItem {
        return UploadFaceImageItem(
            code = code,
            message = message
        )
    }
}