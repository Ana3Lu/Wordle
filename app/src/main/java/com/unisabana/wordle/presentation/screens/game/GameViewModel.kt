package com.unisabana.wordle.presentation.screens.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.unisabana.wordle.data.getRandomWord

class GameViewModel: ViewModel() {

    // Atributos
    var solution by mutableStateOf(getRandomWord())
        private set
    var currentWord by mutableStateOf("")
        private set
    var attempts by mutableStateOf(listOf<String>())
        private set

    // Funciones
    fun onKeyPressed(letter: Char) {
        if (currentWord.length < 5) {
            currentWord += letter
        }
    }

    fun onRemoveLetter() {
        if (currentWord.isNotEmpty()) {
            currentWord = currentWord.dropLast(1)
        }
    }

    fun onSubmit(
        onWin: () -> Unit = {},
        onLose: () -> Unit = {}
    ) {
        if (currentWord.length == 5) {
            attempts += currentWord
            if (currentWord.equals(solution, ignoreCase = true)) {
                onWin()
            } else if (attempts.size >= 6) {
                onLose()
            }
            currentWord = ""
        }
    }

    fun resetGame() {
        attempts = emptyList()
        solution = getRandomWord()
        currentWord = ""
    }
}