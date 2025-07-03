package com.example.seq_test_task.ui.movies_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.seq_test_task.data.models.MoviesListUiState
import com.example.seq_test_task.ui.composable.MovieCard
import com.example.seq_test_task.ui.composable.ListHeader
import com.example.seq_test_task.ui.composable.GenreTab

@Composable
fun MoviesListScreen(
    state: MoviesListUiState,
    onGenreClick: (String) -> Unit,
    onMovieClick: (Long) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        item(span = { GridItemSpan(maxLineSpan) }) {
            ListHeader(title = "Жанры")
        }

        items(
            items = state.allGenres,
            key = { it },
            span = { GridItemSpan(maxLineSpan) }
        ) { genre ->
            if (genre.isNotEmpty()) {
                GenreTab(
                    genre = genre,
                    isSelected = genre == state.selectedGenre,
                    onGenreClick = onGenreClick
                )
            }
        }

        item(span = { GridItemSpan(maxLineSpan) }) {
            ListHeader(title = "Фильмы")
        }

        items(items = state.movies, key = { it.id }) { movie ->
            MovieCard(movie = movie, onMovieClick = onMovieClick)
        }
    }
}