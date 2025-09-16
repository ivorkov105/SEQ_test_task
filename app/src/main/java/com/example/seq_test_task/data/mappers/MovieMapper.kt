package com.example.seq_test_task.data.mappers

import com.example.seq_test_task.data.local.model.MovieEntity
import com.example.seq_test_task.data.remote.model.MovieRemoteDto
import com.example.seq_test_task.domain.model.Movie

fun MovieRemoteDto.toEntity(): MovieEntity {
    return MovieEntity(
        id = this.id,
        localizedName = this.localizedName,
        name = this.name,
        year =  this.year,
        rating = this.rating,
        imageUrl = this.imageUrl,
        description = this.description,
        genres = this.genres
    )
}

fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = this.id,
        localizedName = this.localizedName,
        name = this.name,
        year =  this.year,
        rating = this.rating!!,
        imageUrl = this.imageUrl!!,
        description = this.description!!,
        genres = this.genres
    )
}