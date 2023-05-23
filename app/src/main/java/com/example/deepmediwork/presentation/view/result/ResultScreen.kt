package com.example.deepmediwork.presentation.view.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.deepmediwork.domain.remote.model.user_info.UserInfoItem
import com.example.deepmediwork.ui.theme.DeepMediColor

@Composable
fun ResultScreen(
    navController: NavHostController,
    userInfo: UserInfoItem
) {
    Column {
        ResultTopAppBar(navController = navController)
        DefaultInfoText()
        DefaultUserInfo(userInfo = userInfo)
        Divider(
            modifier = Modifier.padding(top = 16.dp, start = 20.dp, end = 20.dp),
            color = DeepMediColor.Gray
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
                color = DeepMediColor.Red
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
    Divider(color = DeepMediColor.Gray)
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
            color = DeepMediColor.Red
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
            color = DeepMediColor.Red
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
                        .border(width = 0.3.dp, color = DeepMediColor.Gray)
                        .background(color = DeepMediColor.Pink),
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
                        CheckUserHealthInfo(userInfoList[index], index)
                    }
                }
            }
        }
    )
}