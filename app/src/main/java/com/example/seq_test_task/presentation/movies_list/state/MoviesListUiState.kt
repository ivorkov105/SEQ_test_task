package com.example.seq_test_task.presentation.movies_list.state

import com.example.seq_test_task.data.local.model.MovieEntity

data class MoviesListUiState(
    val allGenres: List<String> = emptyList(),
    val selectedGenre: String? = null,
    val movieEntities: List<MovieEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)