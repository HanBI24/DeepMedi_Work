package com.example.deepmediwork.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deepmediwork.domain.remote.model.UploadFaceImageItem
import com.example.deepmediwork.domain.remote.repository.UploadFaceImageRepository
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
    private val _stateCode = MutableStateFlow(UploadFaceImageItem(0, "NULL"))
    val stateCode = _stateCode.asStateFlow()

    fun onUploadFaceImage(file: File) {
        viewModelScope.launch {
            _stateCode.value = uploadFaceImageRepository.uploadFaceImage(file)
            println("onSuccess: ${_stateCode.value.message}")
            println("onSuccess: ${file.path}")
        }
    }
}