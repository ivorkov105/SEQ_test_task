package com.example.seq_test_task.data.repositories

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.projectexcursions.databases.AppDatabase
import com.example.seq_test_task.data.daos.MovieDao
import com.example.seq_test_task.data.network.MoviesService
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStreamReader

@RunWith(AndroidJUnit4::class)
class MovieRepositoryImplTest {

    private lateinit var repository: MovieRepository
    private lateinit var movieDao: MovieDao
    private lateinit var db: AppDatabase
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: MoviesService
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()

        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesService::class.java)

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        movieDao = db.movieDao()

        repository = MovieRepositoryImpl(apiService, movieDao)
    }

    @After
    fun tearDown() {
        db.close()
        mockWebServer.shutdown()
    }

    @Test
    fun refreshMovies_successfulResponse_dataIsSavedToDb() = runTest {
        val responseBody = readJsonFromAssets("success_response.json")
        mockWebServer.enqueue(MockResponse().setBody(responseBody).setResponseCode(200))

        val result = repository.refreshMovies()

        assertThat(result.isSuccess).isTrue()

        val moviesFromDb = movieDao.getAllMovies().first()
        assertThat(moviesFromDb).hasSize(1)
        assertThat(moviesFromDb[0].localizedName).isEqualTo("Тестовый фильм из сети")
    }

    @Test
    fun refreshMovies_errorResponse_returnsFailureAndDbIsEmpty() = runTest {
        mockWebServer.enqueue(MockResponse().setResponseCode(404))

        val result = repository.refreshMovies()

        assertThat(result.isFailure).isTrue()

        val moviesFromDb = movieDao.getAllMovies().first()
        assertThat(moviesFromDb).isEmpty()
    }

    private fun readJsonFromAssets(fileName: String): String {
        return InputStreamReader(context.assets.open(fileName)).use { it.readText() }
    }
}