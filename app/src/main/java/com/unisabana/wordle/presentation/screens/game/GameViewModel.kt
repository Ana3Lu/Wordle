package com.unisabana.wordle.presentation.screens.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unisabana.wordle.data.ScoreEntity
import com.unisabana.wordle.data.ScoreRepository
import com.unisabana.wordle.data.WordLength
import kotlinx.coroutines.launch

class GameViewModel(
    private val scoreRepository: ScoreRepository
): ViewModel() {

    // Attributes
    var wordLength by mutableStateOf(WordLength.FIVE)
        private set
    var maxAttempts by mutableIntStateOf(6)
        private set
    var solution by mutableStateOf(wordLength.getRandomWord())
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

    // Func
    fun setDifficulty(length: WordLength) {
        wordLength = length
        maxAttempts = when (length) {
            WordLength.FOUR -> 7
            WordLength.SIX -> 5
            else -> 6
        }
        resetGame()
    }

    fun onKeyPressed(letter: Char) {
        if (currentWord.length < wordLength.size) {
            currentWord += letter
        }
    }

    fun onRemoveLetter() {
        if (currentWord.isNotEmpty()) {
            currentWord = currentWord.dropLast(1)
        }
    }

    fun onSubmit() {
        if (currentWord.length == wordLength.size) {
            attempts += currentWord
            if (currentWord.equals(solution, ignoreCase = true)) {
                val points = (maxAttempts - attempts.size + 1) * 10 // More points for fewer attempts
                showMessage = "You win!"
                isGameFinished = true
                pendingScore = points
            } else if (attempts.size >= maxAttempts) {
                showMessage = "You lose... \nThe word was $solution"
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
        solution = wordLength.getRandomWord()
        currentWord = ""
        isGameFinished = false
        pendingScore = null
        showMessage = null
    }
}