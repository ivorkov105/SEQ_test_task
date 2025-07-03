package com.example.seq_test_task.utils

import androidx.room.TypeConverter

class Converters {
    private val GENRE_SEPARATOR = ", "

    @TypeConverter
    fun fromGenresList(genres: List<String>?): String? {
        return genres?.joinToString(separator = GENRE_SEPARATOR)
    }

    @TypeConverter
    fun toGenresList(genresString: String?): List<String>? {
        return genresString?.split(GENRE_SEPARATOR)?.map { it.trim() }
    }
}