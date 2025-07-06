package com.example.seq_test_task.di

import android.app.Application
import androidx.room.Room
import com.example.projectexcursions.databases.AppDatabase
import com.example.seq_test_task.data.daos.MovieDao
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
class DataModule {
    @Single
    fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "movies-db")
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Single
    fun provideMovieDao(database: AppDatabase): MovieDao {
        return database.movieDao()
    }
}