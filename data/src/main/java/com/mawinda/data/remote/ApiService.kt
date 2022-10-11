package com.mawinda.data.remote

import com.mawinda.data.remote.model.response.TrendingMovies
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/3/trending/all/week")
    suspend fun trending(): Response<TrendingMovies>

}