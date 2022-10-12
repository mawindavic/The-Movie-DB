package com.mawinda.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mawinda.data.local.entities.Genre
import com.mawinda.data.local.entities.GenreDao
import com.mawinda.data.local.entities.Movie
import com.mawinda.data.local.entities.MovieDao

@Database(entities = [Movie::class, Genre::class], exportSchema = false, version = 1)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao

    companion object {
        const val DATABASE_NAME = "the_movies_db"
    }


}