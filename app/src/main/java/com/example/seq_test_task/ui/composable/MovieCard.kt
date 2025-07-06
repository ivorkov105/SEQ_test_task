package com.example.seq_test_task.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.seq_test_task.R
import com.example.seq_test_task.data.models.Movie

@Composable
fun MovieCard(
    movie: Movie,
    onMovieClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .clickable { onMovieClick(movie.id) }
    ) {
        if (movie.imageUrl != null) {
            MoviePic(movie)
        } else MovieItemPlaceholder(modifier)
        Text(
            text = movie.localizedName,
            modifier = Modifier.padding(top = 8.dp),
            maxLines = 2,
            minLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}