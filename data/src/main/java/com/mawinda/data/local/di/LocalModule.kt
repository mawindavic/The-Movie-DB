package com.mawinda.data.local.di

import android.app.Application
import androidx.room.Room
import com.mawinda.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesLocalDb(application: Application): MovieDatabase =
        Room.databaseBuilder(application, MovieDatabase::class.java, MovieDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    fun providesMovieDao(database: MovieDatabase) = database.movieDao()

    @Provides
    fun providesGenreDao(database: MovieDatabase) = database.genreDao()
}