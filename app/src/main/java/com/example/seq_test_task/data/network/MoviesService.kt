package com.example.seq_test_task.data.network

import com.example.seq_test_task.data.models.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {
    @GET("films.json")
    suspend fun getMovies(): Response<MoviesResponse>
}