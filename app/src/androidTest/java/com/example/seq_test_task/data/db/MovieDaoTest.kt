package com.example.seq_test_task.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.projectexcursions.databases.AppDatabase
import com.example.seq_test_task.data.daos.MovieDao
import com.example.seq_test_task.data.models.Movie
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    private lateinit var movieDao: MovieDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        movieDao = db.movieDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAllAndGetAllMovies_returnsCorrectMovies() = runTest {
        val movies = listOf(
            Movie(1, "Фильм А", "Movie A", 2020, 8.0, "", "", listOf("драма")),
            Movie(2, "Фильм Б", "Movie B", 2021, 9.0, "", "", listOf("комедия"))
        )

        movieDao.insertAll(movies)

        val allMovies = movieDao.getAllMovies().first()
        assertThat(allMovies).isEqualTo(movies)
    }

    @Test
    fun getFilmById_returnsCorrectMovie() = runTest {
        val movies = listOf(
            Movie(1, "Фильм А", "Movie A", 2020, 8.0, "", "", listOf("драма")),
            Movie(2, "Фильм Б", "Movie B", 2021, 9.0, "", "", listOf("комедия"))
        )
        movieDao.insertAll(movies)

        val movie = movieDao.getMovieById(2).first()
        assertThat(movie).isNotNull()
        assertThat(movie?.localizedName).isEqualTo("Фильм Б")
    }

    @Test
    fun clearAll_emptiesTheTable() = runTest {
        val movies = listOf(Movie(1, "Фильм А", "Movie A", 2020, 8.0, "", "", listOf("драма")))
        movieDao.insertAll(movies)

        var allMovies = movieDao.getAllMovies().first()
        assertThat(allMovies).isNotEmpty()

        movieDao.clearAll()

        allMovies = movieDao.getAllMovies().first()
        assertThat(allMovies).isEmpty()
    }
}