package com.example.seq_test_task.presentation.movies_details.state

import com.example.seq_test_task.data.local.model.MovieEntity

data class MoviesDetailsUiState(
    val movieEntity: MovieEntity? = null,
    val isLoading: Boolean = true
)