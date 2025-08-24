package com.unisabana.wordle.presentation.screens.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unisabana.wordle.data.ScoreEntity
import com.unisabana.wordle.data.ScoreRepository
import com.unisabana.wordle.data.getRandomWord
import kotlinx.coroutines.launch

class GameViewModel(
    private val scoreRepository: ScoreRepository
): ViewModel() {

    // Atributos
    var solution by mutableStateOf(getRandomWord())
        private set
    var currentWord by mutableStateOf("")
        private set
    var attempts by mutableStateOf(listOf<String>())
        private set
    var showMessage by mutableStateOf<String?>(null)
        private set
    var isGameFinished by mutableStateOf(false)
        private set
    var pendingScore by mutableStateOf<Int?>(null)
        private set
    var scores by mutableStateOf<List<ScoreEntity>>(emptyList())
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

    fun onSubmit() {
        if (currentWord.length == 5) {
            attempts += currentWord
            if (currentWord.equals(solution, ignoreCase = true)) {
                val points = (6 - attempts.size) * 10 // More points for fewer attempts
                showMessage = "You win!"
                isGameFinished = true
                pendingScore = points
            } else if (attempts.size >= 6) {
                showMessage = "You lose..."
                isGameFinished = true
                pendingScore = 0
            }
            currentWord = ""
        }
    }

    fun clearMessage() {
        showMessage = null
    }

    fun saveGameResult(name: String, points: Int) {
        viewModelScope.launch {
            scoreRepository.saveScore(
                name = name,
                score = points,
                solution = solution,
                attempts = attempts
            )
        }
    }

    init {
        viewModelScope.launch {
            scoreRepository.getAllScores().collect { list ->
                scores = list
            }
        }
    }

    fun getAllScores(): List<ScoreEntity> = scores

    fun resetGame() {
        attempts = emptyList()
        solution = getRandomWord()
        currentWord = ""
    }
}