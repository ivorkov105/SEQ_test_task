package com.example.seq_test_task.presentation.movie_details

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.example.seq_test_task.data.local.model.MovieEntity
import com.example.seq_test_task.domain.repository.MovieRepository
import com.example.seq_test_task.presentation.movies_details.DetailsViewModel // Проверь правильность импорта
import com.example.seq_test_task.util.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class MovieDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockRepository: MovieRepository = mockk()
    private lateinit var viewModel: DetailsViewModel

    private val testMovieId = 123L
    private val testMovieEntity = MovieEntity(
        id = testMovieId,
        localizedName = "Тестовый фильм",
        name = "Test Movie",
        year = 2023,
        rating = 8.5,
        imageUrl = null,
        description = "Описание",
        genres = listOf("тест")
    )

    @Test
    fun `init - state correctly emits movie from repository`() = runTest {
        every { mockRepository.getMovieById(testMovieId) } returns flowOf(testMovieEntity)

        val savedStateHandle = SavedStateHandle().apply {
            set("movieId", testMovieId)
        }

        viewModel = DetailsViewModel(mockRepository, savedStateHandle)

        viewModel.movieEntityState.test {
            val movie = awaitItem()

            assertThat(movie.id).isEqualTo(testMovieId)
            assertThat(movie.name).isEqualTo("Test Movie")

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `init - state holds initial value if repository emits null`() = runTest {
        every { mockRepository.getMovieById(testMovieId) } returns flowOf(null)

        val savedStateHandle = SavedStateHandle().apply {
            set("movieId", testMovieId)
        }

        viewModel = DetailsViewModel(mockRepository, savedStateHandle)

        viewModel.movieEntityState.test {
            val movie = awaitItem()
            assertThat(movie.id).isEqualTo(-1L)
            expectNoEvents()
        }
    }
}