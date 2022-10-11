package com.mawinda.data.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mawinda.data.domain.sources.MovieSource
import com.mawinda.data.local.entities.GenreDao
import com.mawinda.data.local.entities.MovieDao
import com.mawinda.data.remote.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityRetainedScoped
class MovieDBRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val movieDao: MovieDao,
    private val genreDao: GenreDao
) {


    @OptIn(ExperimentalPagingApi::class)
    fun movies() =
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MovieSource(remoteDataSource, movieDao),
            pagingSourceFactory = { movieDao.movies() }).flow

    suspend fun genres() {
        with(remoteDataSource.genre()) {
            if (isSuccess) {
                data?.let { movieGenre ->
                    CoroutineScope(Dispatchers.IO).launch {
                        genreDao.insert(*movieGenre.genres.map { it.toGenre() }.toTypedArray())
                    }

                }
            }
        }
    }

}