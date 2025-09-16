package com.example.seq_test_task.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.seq_test_task.data.local.model.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieEntities: List<MovieEntity>)

    @Query("SELECT * FROM movies ORDER BY localizedName ASC")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE genres LIKE '%' || :genre || '%' ORDER BY localizedName ASC")
    fun getMoviesByGenre(genre: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Long): Flow<MovieEntity?>

    @Query("DELETE FROM movies")
    suspend fun clearAll()
}