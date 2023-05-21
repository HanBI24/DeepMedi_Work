package com.example.deepmediwork.data.remote.dto.user_info

data class UserInfoDto(
    val alcohol: Boolean,
    val bpm: Int,
    val cumulant_minus_point: Int,
    val dia: Int,
    val fatigue: Int,
    val name: String,
    val profile: String,
    val resp: Int,
    val spo2: Int,
    val stress: Int,
    val sys: Int,
    val temp: Double
)