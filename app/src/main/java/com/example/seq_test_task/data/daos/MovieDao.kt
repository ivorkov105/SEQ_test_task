package com.example.seq_test_task.data.daos

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

    @Query("SELECT * FROM movies ORDER BY localizedName ASC")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE genres LIKE '%' || :genre || '%' ORDER BY localizedName ASC")
    fun getMoviesByGenre(genre: String): Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Long): Flow<Movie?>

    @Query("DELETE FROM movies")
    suspend fun clearAll()
}