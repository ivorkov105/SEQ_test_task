package com.example.seq_test_task.domain.usecases

import com.example.seq_test_task.domain.repository.MovieRepository
import org.koin.core.annotation.Factory

@Factory
class RefreshMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return movieRepository.refreshMovies()
    }
}