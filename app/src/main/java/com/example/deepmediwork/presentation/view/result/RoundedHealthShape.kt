package com.example.deepmediwork.presentation.view.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deepmediwork.ui.theme.DeepMediColor

@Composable
fun RoundedHealthShapeNormal() {
    Box(
        modifier = Modifier
            .size(40.dp, 20.dp)
            .background(
                color = DeepMediColor.NormalGreen,
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
                color = DeepMediColor.CareYellow,
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
                color = DeepMediColor.WarnOrange,
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
                color = DeepMediColor.Red,
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