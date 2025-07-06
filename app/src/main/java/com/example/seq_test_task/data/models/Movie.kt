package com.example.seq_test_task.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("localized_name")
    val localizedName: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("year")
    val year: Int,

    @SerializedName("rating")
    val rating: Double? = 0.0,

    @SerializedName("image_url")
    val imageUrl: String?,

    @SerializedName("description")
    val description: String? = "Описание кто то съел",

    @SerializedName("genres")
    val genres: List<String> = emptyList()
)