package com.example.seq_test_task.util

import com.example.seq_test_task.data.models.Movie
import com.example.seq_test_task.data.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeMovieRepository: MovieRepository {

    private val moviesFlow = MutableStateFlow<List<Movie>>(emptyList())

    fun setMovies(movies: List<Movie>) {
        moviesFlow.value = movies
    }

    override fun getMoviesStream(): Flow<List<Movie>> {
        return moviesFlow
    }

    override fun getMovieById(movieId: Long): Flow<Movie?> {
        return moviesFlow.map { movies ->
            movies.find { it.id == movieId }
        }
    }

    override fun getGenresStream(): Flow<List<String>> {
        return moviesFlow.map { movies ->
            movies.flatMap { it.genres }.distinct().sorted()
        }
    }

    override suspend fun refreshMovies(): Result<Unit> {
        return Result.success(Unit)
    }
}