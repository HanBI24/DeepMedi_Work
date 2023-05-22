package com.example.deepmediwork.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deepmediwork.domain.remote.model.upload.UploadFaceImageItem
import com.example.deepmediwork.domain.remote.model.user_info.UserInfoItem
import com.example.deepmediwork.domain.remote.repository.upload.UploadFaceImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val uploadFaceImageRepository: UploadFaceImageRepository
) : ViewModel() {

    private val _stateCode = mutableStateOf(UploadFaceImageItem(-1, "NULL"))
    val stateCode: State<UploadFaceImageItem> = _stateCode

    fun onUploadFaceImage(file: File) {
        viewModelScope.launch {
            _stateCode.value = uploadFaceImageRepository.uploadFaceImage(file)
        }
    }

    fun resetState() {
        _stateCode.value = UploadFaceImageItem(-1, "NULL")
    }
}