package com.mawinda.data.remote

import com.mawinda.data.remote.model.StatusResponseWithData
import com.mawinda.data.remote.model.response.TrendingMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) : SafeNetworkApi() {
    private val dispatchers: CoroutineDispatcher by lazy {
        Dispatchers.IO
    }

    suspend fun trendingMovies(page: Int): StatusResponseWithData<TrendingMovies> {
        return with(dispatchers) {
            apiRequest {
                apiService.trending(page = page)
            }.toStatusResponse()
        }
    }
}