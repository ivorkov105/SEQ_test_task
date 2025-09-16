package com.example.seq_test_task.domain.repository

import com.example.seq_test_task.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesStream(): Flow<List<Movie>>

    fun getMovieById(filmId: Long): Flow<Movie?>

    fun getGenresStream(): Flow<List<String>>

    suspend fun refreshMovies(): Result<Unit>
}