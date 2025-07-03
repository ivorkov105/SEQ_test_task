package com.example.seq_test_task.data.repositories

import com.example.seq_test_task.data.db.MovieDao
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

    override fun getFilmsStream(): Flow<List<Movie>> {
        return movieDao.getAllFilms()
    }

    override fun getFilmById(filmId: Int): Flow<Movie?> {
        return movieDao.getFilmById(filmId)
    }

    override fun getGenresStream(): Flow<List<String>> {
        return movieDao.getAllFilms().map { films ->
            films.flatMap { it.genres }.distinct().sorted()
        }
    }

    override suspend fun refreshFilms(): Result<Unit> {
        return try {
            val response = movieService.getFilms()
            movieDao.insertAll(response.body()?.movies ?: emptyList())
            Result.success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}