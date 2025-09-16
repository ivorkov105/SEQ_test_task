package com.example.seq_test_task.data.local.database

import android.content.Context
import com.example.seq_test_task.data.local.daos.MovieDao
import com.example.seq_test_task.data.local.model.MovieEntity
import com.example.seq_test_task.data.local.utils.Converters

@Database(entities = [MovieEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}