package com.example.deepmediwork.presentation.view.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.deepmediwork.domain.remote.model.user_info.UserInfoItem
import com.example.deepmediwork.presentation.viewmodel.ResultScreenViewModel

@Composable
fun ResultScreen(
    navController: NavHostController
) {
    val resultScreenViewModel: ResultScreenViewModel = hiltViewModel()
    val userInfo = resultScreenViewModel.userInfoState.value

    Column {
        ResultTopAppBar(navController = navController)
        DefaultInfoText()
        DefaultUserInfo(userInfo = userInfo)
        Divider(
            modifier = Modifier.padding(top = 32.dp, start = 20.dp, end = 20.dp),
            color = Color(0xFFD8D8D8)
        )
        HealthStateInfoText()
        UserHealthInfoLazyGrid(userInfo)
    }
}

@Composable
fun ResultTopAppBar(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, bottom = 20.dp)
                .align(Alignment.CenterStart),
        ) {
            Text(
                text = "다시 측정",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFFD03843)
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 20.dp)
                .align(Alignment.Center),
            text = "측정 결과",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        )
    }
    Divider(color = Color(0xFFD8D8D8))
}

@Composable
fun DefaultInfoText() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp)
                .align(Alignment.CenterStart),
            text = "기본 정보",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = Color(0xFFD03843)
        )
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DefaultUserInfo(
    userInfo: UserInfoItem
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(top = 16.dp, start = 32.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                rememberImagePainter(userInfo.profile),
                contentDescription = "User profile image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(120.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 32.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "이름: ${userInfo.name}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = "운전자 누적 벌점: ${userInfo.cumulant_minus_point}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                )
            }
        }
    }
}

@Composable
fun HealthStateInfoText() {
    Column(
        modifier = Modifier.padding(top = 16.dp, start = 32.dp, end = 32.dp)
    ) {
        Text(
            text = "건강 상태 │",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = Color(0xFFD03843)
        )
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "주의가 필요합니다. 기름진 음식은 자제하시고 건강은 예방이 중요하다는 것을 잊지 마세요! 자세한 설명을 보고 싶다면 인쇄하기 버튼을 눌러주세요.",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            ),
        )
    }
}

@Composable
fun UserHealthInfoLazyGrid(userInfo: UserInfoItem) {
    val textInfoList = listOf(
        "분당 심박수",
        "심혈관 건강",
        "분당 호흡수",
        "피로도",
        "스트레스",
        "체온",
        "알코올 농도",
        "SpO2",
    )
    val userInfoList = listOf(
        userInfo.bpm,
        "${userInfo.sys}/${userInfo.dia}",
        userInfo.resp,
        userInfo.fatigue,
        userInfo.stress,
        String.format("%.1f", userInfo.temp),
        if (userInfo.alcohol) "검출" else "미검출",
        userInfo.spo2
    )
    val textBackList = listOf(
        "회/분",
        "",
        "회/분",
        "",
        "",
        "℃",
        "",
        "%"
    )

    LazyVerticalGrid(
        modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp),
        columns = GridCells.Fixed(4),
        content = {
            items(textInfoList.size) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(color = Color(0xFFFDF5F6)),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = textInfoList[index],
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )
                        Text(
                            modifier = Modifier.padding(2.dp),
                            text = "${userInfoList[index]}${textBackList[index]}",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                        )
                        RoundedHealthCheck(userInfoList[index], index)
                    }
                }
            }
        }
    )
}

@Composable
fun RoundedHealthCheck(
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
        1 -> {
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

@Composable
fun RoundedHealthShapeNormal() {
    Box(
        modifier = Modifier
            .size(40.dp, 20.dp)
            .background(
                color = Color(0xFF66B300),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "정상",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = Color.White
        )
    }
}

@Composable
fun RoundedHealthShapeCare() {
    Box(
        modifier = Modifier
            .size(40.dp, 20.dp)
            .background(
                color = Color(0xFFFFD000),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "주의",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = Color.White
        )
    }
}

@Composable
fun RoundedHealthShapeWarn() {
    Box(
        modifier = Modifier
            .size(40.dp, 20.dp)
            .background(
                color = Color(0xFFFF6C00),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "경고",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = Color.White
        )
    }
}

@Composable
fun RoundedHealthShapeDanger() {
    Box(
        modifier = Modifier
            .size(40.dp, 20.dp)
            .background(
                color = Color(0xFFDF0000),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "위험",
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = Color.White
        )
    }
}