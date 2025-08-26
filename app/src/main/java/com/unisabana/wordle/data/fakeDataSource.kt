package com.unisabana.wordle.data

enum class WordLength(val size: Int, val words: List<String>) {
    FOUR(
        4, listOf(
            "BLUE",
            "DESK",
            "FISH",
            "FOUR",
            "GOLD",
            "LAMP",
            "MILK",
            "PINK",
            "ROAD",
            "TREE"
        )
    ),
    FIVE(
        5, listOf(
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
    ),
    SIX(
        6, listOf(
            "ORANGE",
            "JACKET",
            "SILVER",
            "WONDER",
            "MARKET",
            "FROZEN",
            "BOTTLE",
            "HAMMER",
            "PENCIL",
            "SUMMER"
        )
    );

    fun getRandomWord(): String = words.random()
}
