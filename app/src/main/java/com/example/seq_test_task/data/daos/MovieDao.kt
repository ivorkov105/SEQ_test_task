package com.example.seq_test_task.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seq_test_task.data.models.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM films ORDER BY localizedName ASC")
    fun getAllFilms(): Flow<List<Movie>>

    @Query("SELECT * FROM films WHERE genres LIKE '%' || :genre || '%' ORDER BY localizedName ASC")
    fun getFilmsByGenre(genre: String): Flow<List<Movie>>

    @Query("SELECT * FROM films WHERE id = :filmId")
    fun getFilmById(filmId: Int): Flow<Movie?>

    @Query("DELETE FROM films")
    suspend fun clearAll()
}