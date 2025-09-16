package com.example.seq_test_task.domain.model

data class Movie(
    val id: Long,
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Double,
    val imageUrl: String,
    val description: String,
    val genres: List<String>
)