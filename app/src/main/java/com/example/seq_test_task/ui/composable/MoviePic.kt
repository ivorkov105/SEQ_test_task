package com.example.seq_test_task.ui.composable

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.seq_test_task.R
import com.example.seq_test_task.data.models.Movie

@Composable
fun MoviePic(
    movie: Movie,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.imageUrl)
            .crossfade(true)
            .placeholder(R.drawable.movie_item_placeholder)
            .error(R.drawable.movie_item_placeholder)
            .build(),
        contentDescription = movie.localizedName,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.7f),
        contentScale = ContentScale.Crop
    )
}