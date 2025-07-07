package com.example.seq_test_task.ui.movies_list

import app.cash.turbine.test
import com.example.seq_test_task.data.models.Movie
import com.example.seq_test_task.data.models.MoviesListUiState
import com.example.seq_test_task.data.repositories.MovieRepository
import com.example.seq_test_task.util.MainDispatcherRule
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MoviesListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val mockRepository: MovieRepository = mockk()

    private lateinit var viewModel: ListViewModel

    private val testFilms = listOf(
        Movie(1, "Фильм 1", "Film 1", 2020, 8.0, "", "Desc 1", listOf("драма", "боевик")),
        Movie(2, "Фильм 2", "Film 2", 2021, 8.5, "", "Desc 2", listOf("комедия")),
        Movie(3, "Фильм 3", "Film 3", 2022, 9.0, "", "Desc 3", listOf("драма"))
    )

    private val allGenresSorted = listOf("боевик", "драма", "комедия")

    @Before
    fun setUp() {
        coEvery { mockRepository.refreshMovies() } returns Result.success(Unit)
        every { mockRepository.getMoviesStream() } returns flowOf(testFilms)
    }

    @Test
    fun `init - state contains correct films and genres after initialization`() = runTest {
        viewModel = ListViewModel(mockRepository)

        advanceUntilIdle()

        viewModel.uiState.test {
            val finalState = awaitItem()

            assertThat(finalState.isLoading).isFalse()
            assertThat(finalState.movies).hasSize(3)
            assertThat(finalState.movies.map { it.name }).containsExactly("Film 1", "Film 2", "Film 3")
            assertThat(finalState.allGenres).isEqualTo(allGenresSorted)
            assertThat(finalState.error).isNull()

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onGenreSelected - updates state with correctly filtered films`() = runTest {
        viewModel = ListViewModel(mockRepository)

        viewModel.uiState.test {
            val initialState = awaitItem()
            assertThat(initialState.isLoading).isFalse()

            viewModel.onGenreSelected("драма")

            val filteredState = awaitItem()
            assertThat(filteredState.selectedGenre).isEqualTo("драма")
            assertThat(filteredState.movies).hasSize(2)
            assertThat(filteredState.movies.map { it.name }).containsExactly("Film 1", "Film 3")

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `onGenreSelected - deselects genre on second click`() = runTest {
        viewModel = ListViewModel(mockRepository)

        viewModel.uiState.test {
            awaitItem()

            viewModel.onGenreSelected("драма")
            awaitItem()

            viewModel.onGenreSelected("драма")
            val resetState = awaitItem()

            assertThat(resetState.selectedGenre).isNull()
            assertThat(resetState.movies).hasSize(3)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `loadFilms with network error - error state is updated`() = runTest {
        val errorMessage = "Network Error"
        coEvery { mockRepository.refreshMovies() } returns Result.failure(RuntimeException(errorMessage))
        every { mockRepository.getMoviesStream() } returns flowOf(emptyList())

        viewModel = ListViewModel(mockRepository)

        advanceUntilIdle()

        viewModel.uiState.test {
            val finalState = awaitItem()

            assertThat(finalState.isLoading).isFalse()
            assertThat(finalState.error).isEqualTo(errorMessage)

            cancelAndIgnoreRemainingEvents()
        }
    }
}