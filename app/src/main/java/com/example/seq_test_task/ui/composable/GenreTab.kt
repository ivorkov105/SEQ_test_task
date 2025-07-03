package com.example.seq_test_task.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GenreTab(
    genre: String,
    isSelected: Boolean,
    onGenreClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onGenreClick(genre) }
            .background(
                color = if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent
            )
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = genre.replaceFirstChar { it.uppercase() },
            color = if (isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onBackground
        )
    }
}