package com.example.seq_test_task.data.remote.model

import com.google.gson.annotations.SerializedName

data class MovieRemoteDto(
    @SerializedName("id")
    val id: Long,

    @SerializedName("localized_name")
    val localizedName: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("year")
    val year: Int,

    @SerializedName("rating")
    val rating: Double?,

    @SerializedName("image_url")
    val imageUrl: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("genres")
    val genres: List<String>
)