package com.example.seq_test_task.ui.movies_details

import androidx.lifecycle.SavedStateHandle
import com.example.seq_test_task.data.local.model.MovieEntity
import com.example.seq_test_task.domain.repository.MovieRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

@KoinViewModel
class DetailsViewModel(
    repository: MovieRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val movieId: Long = checkNotNull(savedStateHandle["movieId"])

    val movieEntityState: StateFlow<MovieEntity> =
        repository.getMovieById(movieId)
            .filterNotNull()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = MovieEntity(
                    id = -1L,
                    localizedName = "",
                    name = "",
                    year = 0,
                    rating = null,
                    imageUrl = null,
                    description = null,
                    genres = emptyList()
                )
            )
}