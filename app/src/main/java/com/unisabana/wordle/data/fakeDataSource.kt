package com.unisabana.wordle.data

val words = listOf<String>(
    "APPLE",
    "BEACH",
    "BRAIN",
    "CHAIR",
    "DREAM",
    "GRAPE",
    "MUSIC",
    "PLANT",
    "SMILE",
    "TABLE"
)

fun getRandomWord(): String {
    return words.random()
}
