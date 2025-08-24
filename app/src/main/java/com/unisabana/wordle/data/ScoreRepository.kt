package com.unisabana.wordle.data

import kotlinx.coroutines.flow.Flow

class ScoreRepository(
    private val scoreDao: ScoreDao
) {
    fun getAllScores(): Flow<List<ScoreEntity>> = scoreDao.getAllScores()

    suspend fun saveScore(
        name: String,
        score: Int,
        solution: String,
        attempts: List<String>
    ) {
        scoreDao.insertScore(
            ScoreEntity(
                name = name,
                score = score,
                solution = solution,
                attempts = attempts
            )
        )
    }
}