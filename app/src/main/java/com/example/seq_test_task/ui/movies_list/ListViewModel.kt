package com.example.seq_test_task.ui.movies_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seq_test_task.data.models.MoviesListUiState
import com.example.seq_test_task.data.repositories.MovieRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ListViewModel(
    private val repository: MovieRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(MoviesListUiState())
    val uiState: StateFlow<MoviesListUiState> = _uiState.asStateFlow()

    private val selectedGenre = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            repository.getFilmsStream()
                .combine(selectedGenre) { moviesFromDb, genre ->
                    MoviesListUiState(
                        allGenres = moviesFromDb.flatMap { it.genres }.distinct().sorted(),
                        selectedGenre = genre,
                        movies = if (genre == null) {
                            moviesFromDb
                        } else {
                            moviesFromDb.filter { it.genres.contains(genre) }
                        }.sortedBy { it.localizedName }
                    )
                }.catch { e ->
                    _uiState.update { it.copy(error = e.localizedMessage ?: "Произошла ошибка") }
                }
                .collect { newStateFromDb ->
                    _uiState.update { currentState ->
                        newStateFromDb.copy(
                            isLoading = currentState.isLoading,
                            error = currentState.error
                        )
                    }
                }
        }
        loadFilms()
    }

    fun onGenreSelected(genre: String) {
        if (selectedGenre.value == genre) {
            selectedGenre.value = null
        } else {
            selectedGenre.value = genre
        }
    }

    fun loadFilms() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            repository.refreshFilms()
                .onFailure { error ->
                    _uiState.update { it.copy(error = error.localizedMessage ?: "Ошибка сети") }
                }
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}