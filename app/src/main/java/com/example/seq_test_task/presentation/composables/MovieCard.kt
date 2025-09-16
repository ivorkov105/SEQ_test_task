package com.example.seq_test_task.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.seq_test_task.data.local.model.MovieEntity

@Composable
fun MovieCard(
    movieEntity: MovieEntity,
    onMovieClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .clickable { onMovieClick(movieEntity.id) }
    ) {
        if (movieEntity.imageUrl != null) {
            MoviePic(movieEntity)
        } else MovieItemPlaceholder(modifier)
        Text(
            text = movieEntity.localizedName,
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}