package com.unisabana.wordle.presentation.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.unisabana.wordle.presentation.components.AppButton
import com.unisabana.wordle.presentation.components.Grid
import com.unisabana.wordle.presentation.components.Keyboard
import com.unisabana.wordle.presentation.components.TopBar

@Composable
fun GameScreen(
    onBack: () -> Unit,
    onSubmit: () -> Unit,
    gameViewModel: GameViewModel = viewModel()
) {
    val showMessage = gameViewModel.showMessage
    val isGameFinished = gameViewModel.isGameFinished

    var askName by remember { mutableStateOf(false) }
    var playerName by remember { mutableStateOf("") }


    Scaffold (
        topBar = {
            TopBar("WORDLE", onBack)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFF121213)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 50.dp)
                        .padding(horizontal = 30.dp)
                ) {
                    Grid(
                        solution = gameViewModel::solution.get(),
                        attempts = gameViewModel::attempts.get(),
                        currentWord = gameViewModel::currentWord.get(),
                        size = 65
                    )
                }

                Keyboard(
                    onRemove = gameViewModel::onRemoveLetter,
                    onKeyPressed = gameViewModel::onKeyPressed
                )

                Spacer(modifier = Modifier.height(70.dp))

                AppButton("Submit") {
                    gameViewModel.onSubmit()
                }

                if (showMessage != null) {
                    AlertDialog(
                        onDismissRequest = gameViewModel::clearMessage,
                        title = { Text(showMessage) },
                        confirmButton = {
                            TextButton(onClick = {
                                gameViewModel.clearMessage()
                                if (isGameFinished) {
                                    askName = true
                                }
                            }) {
                                Text("OK")
                            }
                        }

                    )
                }

                if (askName) {
                    AlertDialog(
                        onDismissRequest = { },
                        title = { Text("Save score") },
                        text = {
                            Column {
                                Text("Enter your name:", style = TextStyle(fontWeight = FontWeight.Bold))
                                OutlinedTextField(
                                    value = playerName,
                                    onValueChange = { playerName = it },
                                    label = { Text("Name") }
                                )
                            }
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                gameViewModel.pendingScore?.let { points ->
                                    gameViewModel.saveGameResult(
                                        name = playerName.ifBlank { "Player" },
                                        points = points
                                    )
                                }
                                askName = false
                                onSubmit()
                            }) {
                                Text("Save")
                            }

                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGameScreen() {
    val viewModel: GameViewModel = viewModel()
    GameScreen(
        onBack = {},
        onSubmit = {},
        gameViewModel = viewModel
    )
}