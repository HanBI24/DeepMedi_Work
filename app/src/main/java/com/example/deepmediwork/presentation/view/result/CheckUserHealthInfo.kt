package com.example.deepmediwork.presentation.view.result

import androidx.compose.runtime.Composable

// 사용자 수치에 따른 정보 출력
@Composable
fun CheckUserHealthInfo(
    userInfo: Any,
    index: Int
) {
    val userInfoString = userInfo.toString()
    when (index) {
        0 -> {
            when (userInfoString.toInt()) {
                in 60..80 -> RoundedHealthShapeNormal()
                in 81..100 -> RoundedHealthShapeCare()
                in 101..150 -> RoundedHealthShapeWarn()
                else -> RoundedHealthShapeDanger()
            }
        }
        1 -> {      // sys와 dia 중 더 안 좋은 수치 출력
            val sysDia = userInfoString.split('/')

            val sysData = sysDia[0].toInt()
            val diaData = sysDia[1].toInt()

            val sysResult = when (sysData) {
                in 100..120 -> 1
                in 121..140 -> 2
                in 141..160 -> 3
                else -> 4
            }
            val diaResult = when (diaData) {
                in 50..70 -> 1
                in 71..90 -> 2
                in 91..110 -> 3
                else -> 4
            }

            if (sysResult > diaResult) {
                when (sysResult) {
                    1 -> RoundedHealthShapeNormal()
                    2 -> RoundedHealthShapeCare()
                    3 -> RoundedHealthShapeWarn()
                    4 -> RoundedHealthShapeDanger()
                }
            } else {
                when (diaResult) {
                    1 -> RoundedHealthShapeNormal()
                    2 -> RoundedHealthShapeCare()
                    3 -> RoundedHealthShapeWarn()
                    4 -> RoundedHealthShapeDanger()
                }
            }
        }
        2 -> {
            when (userInfoString.toInt()) {
                in 1..8 -> RoundedHealthShapeNormal()
                in 9..12 -> RoundedHealthShapeCare()
                in 13..16 -> RoundedHealthShapeWarn()
                else -> RoundedHealthShapeDanger()
            }
        }
        3 -> {
            when (userInfoString.toInt()) {
                in 60..80 -> RoundedHealthShapeNormal()
                in 81..100 -> RoundedHealthShapeCare()
                in 101..150 -> RoundedHealthShapeWarn()
                else -> RoundedHealthShapeDanger()
            }
        }
        4 -> {
            when (userInfoString.toInt()) {
                in 0..1 -> RoundedHealthShapeNormal()
                2 -> RoundedHealthShapeCare()
                in 3..4 -> RoundedHealthShapeWarn()
                else -> RoundedHealthShapeDanger()
            }
        }
    }
}