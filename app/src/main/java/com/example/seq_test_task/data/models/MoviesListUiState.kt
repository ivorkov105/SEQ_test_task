package com.example.seq_test_task.data.models

data class MoviesListUiState(
    val allGenres: List<String> = emptyList(),
    val selectedGenre: String? = null,
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)