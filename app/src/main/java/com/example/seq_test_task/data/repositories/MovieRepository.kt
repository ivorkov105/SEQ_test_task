package com.example.seq_test_task.data.repositories

import com.example.seq_test_task.data.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviesStream(): Flow<List<Movie>>

    fun getMovieById(filmId: Long): Flow<Movie?>

    fun getGenresStream(): Flow<List<String>>

    suspend fun refreshMovies(): Result<Unit>
}