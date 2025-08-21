package com.unisabana.wordle.presentation.screens.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    gameViewModel.onSubmit(
                        onWin = {
                            onSubmit()
                        },
                        onLose = {
                            onSubmit()
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
    GameScreen(
        onBack = {},
        onSubmit = {}
    )
}