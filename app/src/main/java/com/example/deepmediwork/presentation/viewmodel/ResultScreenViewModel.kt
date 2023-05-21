package com.example.deepmediwork.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deepmediwork.domain.remote.model.user_info.UserInfoItem
import com.example.deepmediwork.domain.remote.repository.user_info.UserInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultScreenViewModel @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) : ViewModel() {

    private val _userInfoState = mutableStateOf(UserInfoItem(false, 0, 0, 0, 0, "NULL", "NULL", 0, 0, 0, 0, 0.0))
    val userInfoState: State<UserInfoItem> = _userInfoState

    init {
        viewModelScope.launch {
            _userInfoState.value = userInfoRepository.getUserInfo()
        }
    }
}