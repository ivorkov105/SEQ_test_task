package com.example.seq_test_task.data.repositories

import com.example.seq_test_task.data.local.daos.MovieDao
import com.example.seq_test_task.data.mappers.toDomain
import com.example.seq_test_task.data.mappers.toEntity
import com.example.seq_test_task.data.remote.api.MoviesService
import com.example.seq_test_task.domain.model.Movie
import com.example.seq_test_task.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Single

@Single(binds = [MovieRepository::class])
class MovieRepositoryImpl(
    private val movieService: MoviesService,
    private val movieDao: MovieDao
): MovieRepository {

    override fun getMoviesStream(): Flow<List<Movie>> {
        return movieDao.getAllMovies().map { movies ->
            movies.map { it.toDomain() }
        }
    }

    override fun getMovieById(movieId: Long): Flow<Movie?> {
        return movieDao.getMovieById(movieId).map { movie ->
            movie?.toDomain()
        }
    }

    override fun getGenresStream(): Flow<List<String>> {
        return movieDao.getAllMovies().map { movies ->
            movies.flatMap { it.genres }.distinct().sorted()
        }
    }

    override suspend fun refreshMovies(): Result<Unit> {
        return try {
            val response = movieService.getMovies()
            if (response.isSuccessful) {
                val moviesDto = response.body()?.moviesDto ?: emptyList()
                val movieEntities = moviesDto.map { it.toEntity() }
                movieDao.insertAll(movieEntities)
                Result.success(Unit)
            } else {
                Result.failure(Exception("Network request failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}