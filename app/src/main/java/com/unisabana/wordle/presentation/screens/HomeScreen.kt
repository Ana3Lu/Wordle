package com.unisabana.wordle.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.unisabana.wordle.data.WordLength
import com.unisabana.wordle.presentation.components.AppButton
import com.unisabana.wordle.presentation.components.CellType
import com.unisabana.wordle.presentation.components.RowCells

@Composable
fun HomeScreen(onPlay: (WordLength) -> Unit, onLeaderboard: () -> Unit) {
    val sizeExampleCell = 35
    val howToPlayRules = listOf(
        "Each guess must be a valid 4/5/6-letter word.",
        "The color of the tiles will change to show how close your guess was to the word.",
    )

    var askWordLength by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF121213)),
            contentAlignment = Alignment.Center
        ) {
            Column (
                modifier = Modifier
                    .padding(top = 70.dp)
                    .padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row (
                    modifier = Modifier
                        .padding(bottom = 70.dp)
                ) {
                    RowCells(
                        "WORDLE",
                        manualTypes = listOf(
                            CellType.GREEN,
                            CellType.YELLOW,
                            CellType.GRAY,
                            CellType.GREEN,
                            CellType.YELLOW,
                            CellType.GRAY
                        )
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "How to play",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier.padding(bottom = 25.dp)
                    )

                    Text(
                        text = "Guess the Wordle in 6 tries",
                        style = TextStyle(
                            fontSize = 14.sp,
                            color = Color.White
                        ),
                        modifier = Modifier.padding(bottom = 20.dp)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 6.dp)
                            .padding(bottom = 40.dp)
                    ) {
                        items(howToPlayRules) { rule ->
                            BulletListItem(text = rule)
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Examples",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 6.dp)
                        ) {
                            RowCells("WORDY", manualTypes = listOf(CellType.GREEN), size = sizeExampleCell)
                        }

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)) {
                                    append("W ")
                                }
                                append("is in the word and in the correct spot.")
                            },
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .padding(bottom = 2.dp)
                        )
                    }

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 6.dp)
                        ) {
                            RowCells("LIGHT", manualTypes = listOf(CellType.TRANSPARENT, CellType.YELLOW), size = sizeExampleCell)
                        }

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)) {
                                    append("I ")
                                }
                                append("is in the word and in the wrong spot.")
                            },
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .padding(bottom = 2.dp)
                        )
                    }

                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 6.dp)
                        ) {
                            RowCells(
                                "ROGUE",
                                manualTypes = listOf(
                                    CellType.TRANSPARENT,
                                    CellType.TRANSPARENT,
                                    CellType.TRANSPARENT,
                                    CellType.GRAY),
                                size = sizeExampleCell)
                        }

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 14.sp)) {
                                    append("U ")
                                }
                                append("is not in the word in any spot.")
                            },
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .padding(bottom = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(90.dp))

                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppButton("Let's play!") { askWordLength = true }
                    Spacer(modifier = Modifier.height(16.dp))
                    AppButton("Leaderboard", onLeaderboard)
                }

                if (askWordLength) {
                    AlertDialog(
                        onDismissRequest = { askWordLength = false},
                        title = { Text("Word length") },
                        text = {
                            Column {
                               Text("Select difficulty:")
                               Spacer(Modifier.height(8.dp))
                               Row {
                                   Button(onClick = {
                                       askWordLength = false
                                       onPlay(WordLength.FOUR)
                                   }) {
                                       Text("4")
                                   }
                                   Spacer(Modifier.width(8.dp))
                                   Button(onClick = {
                                       askWordLength = false
                                       onPlay(WordLength.FIVE)
                                   }) {
                                       Text("5")
                                   }
                                   Spacer(Modifier.width(8.dp))
                                   Button(onClick = {
                                       askWordLength = false
                                       onPlay(WordLength.SIX)
                                   }) {
                                       Text("6")
                                   }
                               }
                            }
                        },
                        confirmButton = {}
                    )
                }
            }
        }
    }
}

@Composable
fun BulletListItem(text: String, bullet: String = "•") {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Text(
            text = bullet,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(end = 8.dp)
        )
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.White
            ),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        onPlay = {},
        onLeaderboard = {}
    )
}