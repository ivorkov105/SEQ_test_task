package com.example.seq_test_task.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Long = 0,
    val localizedName: String,
    val name: String,
    val year: Int,
    val rating: Double? = 0.0,
    val imageUrl: String?,
    val description: String? = "Описание кто то съел",
    val genres: List<String> = emptyList()
)