package com.example.deepmediwork.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
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
    private val _stateCode = MutableStateFlow(UploadFaceImageItem(-1, "NULL"))
    val stateCode = _stateCode.asStateFlow()

    val stateFile = mutableStateOf(File("test"))

    fun onUploadFaceImage(file: File) {
        println("onSuccess ${file.name}")
        viewModelScope.launch {
            _stateCode.value = uploadFaceImageRepository.uploadFaceImage(file)
        }
        stateFile.value = file
    }

    fun resetState() {
        _stateCode.value = UploadFaceImageItem(-1, "NULL")
        stateFile.value = File("test")
    }
}