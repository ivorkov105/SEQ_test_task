package com.example.seq_test_task.data.remote.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("films")
    val moviesDto: List<MovieRemoteDto>
)