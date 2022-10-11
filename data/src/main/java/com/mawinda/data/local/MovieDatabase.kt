package com.mawinda.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mawinda.data.local.entities.MovieDao
import com.mawinda.data.local.entities.MovieEntity

@Database(entities = [MovieEntity::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        const val DATABASE_NAME = "the_movies_db"
    }
}