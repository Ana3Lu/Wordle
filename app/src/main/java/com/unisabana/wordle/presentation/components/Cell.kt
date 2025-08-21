package com.unisabana.wordle.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class CellType {
    GREEN,
    YELLOW,
    GRAY,
    TRANSPARENT
}

@Composable
fun Cell(character:String, blockType:CellType, sizeCell:Int, icon:Boolean = false) {
    val color = when (blockType) {
        CellType.YELLOW -> Color(0xFFC9B457)
        CellType.GREEN  -> Color(0xFF6BAA65)
        CellType.GRAY   -> Color(0xFF787C7F)
        else            -> Color.Transparent
    }

    val cellShape = if (icon) RoundedCornerShape(10.dp) else RectangleShape

    var cellModifier = Modifier
        .size(sizeCell.dp)
        .padding(2.dp)

    if (blockType == CellType.TRANSPARENT) {
        cellModifier = cellModifier.border(BorderStroke(2.dp, Color(0xFF3A3A3C)), shape = cellShape)
    }

    Box(
        modifier = cellModifier
            .background(color, shape = cellShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            character,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun RowCells(
    str: String,
    wordTarget:String = "",
    size: Int = 55,
    defaultType: CellType = CellType.TRANSPARENT,
    manualTypes: List<CellType>? = null,
    icon: Boolean = false
) {
    Row {
        for (i in str.indices) {
            val letter = str[i].toString()
            val type = when {
                manualTypes != null && i < manualTypes.size -> manualTypes[i] // Manual types

                // Logic game colors
                wordTarget.isNotEmpty() && i < wordTarget.length && letter == wordTarget[i].toString() ->
                    CellType.GREEN
                wordTarget.isNotEmpty() && letter in wordTarget ->
                    CellType.YELLOW
                wordTarget.isNotEmpty() && letter !in wordTarget ->
                    CellType.GRAY
                else -> defaultType
            }

            Cell(letter, type, size, icon = icon)
        }
    }
}

@Composable
fun Grid(solution: String, currentWord: String, attempts: List<String>, size: Int) {
    for (i in 0..6) {
        RowCells("     ", size = size)
    }
}

@Preview
@Composable
fun PreviewCellIcon() {
    RowCells("W", manualTypes = listOf(CellType.GREEN), icon = true)
}

@Preview
@Composable
fun PreviewCellSuccess() {
    RowCells("D", manualTypes = listOf(CellType.GREEN))
}

@Preview
@Composable
fun PreviewCellMissing() {
    RowCells("O", manualTypes = listOf(CellType.YELLOW))
}

@Preview
@Composable
fun PreviewCellNoInWord() {
    RowCells("R", manualTypes = listOf(CellType.GRAY))
}

@Preview
@Composable
fun PreviewCellTyped() {
    RowCells("E")
}

@Preview
@Composable
fun PreviewCellBlank() {
    RowCells(" ")
}

@Preview
@Composable
fun PreviewCells() {
    Column {
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

        RowCells("WORDLE")

        RowCells("      ")
    }
}