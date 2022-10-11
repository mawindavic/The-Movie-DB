package com.mawinda.data.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mawinda.data.domain.sources.MovieSource
import com.mawinda.data.local.entities.MovieDao
import com.mawinda.data.remote.RemoteDataSource
import com.mawinda.data.remote.model.StatusResponseWithData
import com.mawinda.data.remote.model.response.TrendingMovies
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MovieDBRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val movieDao: MovieDao
) {

    @OptIn(ExperimentalPagingApi::class)
    fun movies() =
        Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = MovieSource(remoteDataSource, movieDao),
            pagingSourceFactory = { movieDao.movies() }).flow

}