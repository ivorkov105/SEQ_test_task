package com.example.seq_test_task.domain.usecases

import com.example.seq_test_task.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory

@Factory
class GetGenresStreamUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<List<String>> {
        return movieRepository.getGenresStream()
    }
}