package com.unisabana.wordle.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unisabana.wordle.presentation.components.TopBar

@Composable
fun ScoreScreen(onBack: () -> Unit) {
    val scores = listOf(
        "Name one" to "score",
        "Name two" to "score",
        "Name one" to "score",
        "Name two" to "score",
        "Name one" to "score",
        "Name two" to "score",
        "Name one" to "score",
        "Name two" to "score"
    )

    Scaffold (
        topBar = { TopBar("Leaderboard", onBack) }
    ){ innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF121213)),
            contentAlignment = Alignment.TopCenter
        ) {
            LazyColumn (
                modifier = Modifier
                    .padding(55.dp)
            ) {
                itemsIndexed(scores) { index, (name, score) ->
                    Text(
                        text = "${index + 1}. $name - $score",
                        color = Color.White,
                        modifier = Modifier.padding(10.dp),
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScoreScreen() {
    ScoreScreen(
        onBack = {}
    )
}