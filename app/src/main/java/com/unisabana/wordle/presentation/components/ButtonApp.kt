package com.unisabana.wordle.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppButton(str: String, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .height(35.dp)
            .width(320.dp)
            .background(Color(0xFF6BAA65), shape = RoundedCornerShape(20.dp))
            .clickable(
                onClick = onClick,
                role = Role.Button
            )
            .padding(2.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            str,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp
        )
    }
}

@Preview
@Composable
fun PreviewAppButton() {
    Column {
        AppButton("Let's play!", onClick = {})
        Spacer(modifier = Modifier.height(16.dp))
        AppButton("Submit", onClick = {})
        Spacer(modifier = Modifier.height(16.dp))
        AppButton("Leaderboard", onClick = {})
    }
}

