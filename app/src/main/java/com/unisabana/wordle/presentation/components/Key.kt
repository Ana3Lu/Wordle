package com.unisabana.wordle.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Key(char:Char, onPress: (char: Char) -> Unit, onRemove: () -> Unit) {
    val wideKeyCharacters = setOf("Z", "X", "C", "V", "B", "N", "M")
    val isLastRow = wideKeyCharacters.contains(char.toString())

    Box(
        modifier = Modifier
            .height(43.dp)
            .width(
                when {
                    char == '⌫' -> 45.dp
                    isLastRow -> 38.dp
                    else -> 35.dp
                }
            ).padding(vertical = 1.2.dp, horizontal = 1.4.dp)
            .background(Color(0xFF404752), shape = RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            char.toString(),
            fontSize = if (char == '⌫') 18.sp else 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.clickable {
                if (char == '⌫') {
                    onRemove()
                } else {
                    onPress(char)
                }
            }
        )
    }
}

@Composable
fun RowKeys(
    row: String,
    onRemove: () -> Unit,
    onKeyPressed: (Char) -> Unit,
    extraPadding: Dp = 0.dp
) {
    Row(
        modifier = Modifier.padding(horizontal = extraPadding),
        horizontalArrangement = Arrangement.Center
    ) {
        for (char in row) {
            Key(char, onKeyPressed, onRemove)
        }
    }
}

@Composable
fun Keyboard(onRemove: () -> Unit, onKeyPressed: (Char) -> Unit) {
    val rows = listOf<String>("QWERTYUIOP", "ASDFGHJKLÑ", "ZXCVBNM⌫")

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        for (i in rows.indices) {
            if (i != rows.size - 1) {
                RowKeys(rows[i], onRemove, onKeyPressed)
                if (i == 0) {
                    Spacer(modifier = Modifier.height(2.dp))
                } else {
                    Spacer(modifier = Modifier.height(5.dp))
                }
            } else {
                RowKeys(rows[i], onRemove, onKeyPressed, extraPadding = 32.dp)
            }
        }
    }
}

@Preview
@Composable
fun PreviewKeyboard() {
    Keyboard(onRemove = {}, onKeyPressed = {})
}
