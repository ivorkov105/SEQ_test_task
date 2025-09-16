package com.example.seq_test_task.data.remote.api

import com.example.seq_test_task.data.remote.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MoviesService {
    @GET("films.json")
    suspend fun getMovies(): Response<MoviesResponse>
}