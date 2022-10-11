package com.mawinda.data.domain.sources

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.mawinda.data.local.entities.MovieDao
import com.mawinda.data.local.entities.Movie
import com.mawinda.data.remote.RemoteDataSource
import com.mawinda.data.remote.model.dto.MovieDTO

@OptIn(ExperimentalPagingApi::class)
class MovieSource(private val remoteDataSource: RemoteDataSource, private val movieDao: MovieDao) :
    RemoteMediator<Int, Movie>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        val key = state.anchorPosition ?: 1
        val page = when (loadType) {
            LoadType.REFRESH -> key
            LoadType.PREPEND -> key.plus(1)
            LoadType.APPEND -> if (key == 1) key else key.minus(1)
        }

        val response = remoteDataSource.trendingMovies(page = page)

        if (response.isSuccess.not())
            return MediatorResult.Error(Throwable(response.msg))

        val mData = response.data ?: return MediatorResult.Error(Throwable("Data not found"))

        val dtoMovies: List<MovieDTO> = mData.results

        movieDao.insert(*dtoMovies.map { dto ->
            dto.toMovie()
        }.toTypedArray())

        return MediatorResult.Success(mData.isEndOfPagination)
    }

}