package com.example.seq_test_task.data.models

import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("films")
    val movies: List<Movie>
)