package com.example.seq_test_task.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.seq_test_task.R

@Composable
fun MovieItemPlaceholder(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.movie_item_placeholder),
        contentDescription = "Image placeholder",
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
@Preview
fun MovieItemPlaceholder_prev() {
    Image(
        painter = painterResource(id = R.drawable.movie_item_placeholder),
        contentDescription = "Image placeholder",
        contentScale = ContentScale.Crop
    )
}