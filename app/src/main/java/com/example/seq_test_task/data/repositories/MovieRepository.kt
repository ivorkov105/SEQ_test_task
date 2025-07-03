package com.example.seq_test_task.data.repositories

import com.example.seq_test_task.data.models.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getFilmsStream(): Flow<List<Movie>>

    fun getFilmById(filmId: Int): Flow<Movie?>

    fun getGenresStream(): Flow<List<String>>

    suspend fun refreshFilms(): Result<Unit>
}