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

//enum class category

/*fun getRandomWord(category: Int): String {
    return words.random()
}*/

fun getRandomWord(): String {
    return words.random()
}
