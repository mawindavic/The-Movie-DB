package com.mawinda.data

import com.mawinda.data.local.LocalDataSource
import com.mawinda.data.remote.RemoteDataSource
import com.mawinda.data.remote.model.StatusResponseWithData
import com.mawinda.data.remote.model.response.TrendingMovies
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class MovieDBRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource, private val localDataSource: LocalDataSource
) {

    suspend fun trendingMovies(): StatusResponseWithData<TrendingMovies> {
        return remoteDataSource.trendingMovies()
    }

}