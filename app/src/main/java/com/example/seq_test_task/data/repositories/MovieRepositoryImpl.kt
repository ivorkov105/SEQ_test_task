package com.example.seq_test_task.data.repositories

import com.example.seq_test_task.data.daos.MovieDao
import com.example.seq_test_task.data.models.Movie
import com.example.seq_test_task.data.network.MoviesService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single(binds = [MovieRepository::class])
class MovieRepositoryImpl(
    private val movieService: MoviesService,
    private val movieDao: MovieDao
): MovieRepository {

    override fun getMoviesStream(): Flow<List<Movie>> {
        return movieDao.getAllMovies()
    }

    override fun getMovieById(movieId: Long): Flow<Movie?> {
        return movieDao.getMovieById(movieId)
    }

    override fun getGenresStream(): Flow<List<String>> {
        return movieDao.getAllMovies().map { movies ->
            movies.flatMap { it.genres }.distinct().sorted()
        }
    }

    override suspend fun refreshMovies(): Result<Unit> {
        return try {
            val response = movieService.getMovies()
            movieDao.insertAll(response.body()?.movies ?: emptyList())
            Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}