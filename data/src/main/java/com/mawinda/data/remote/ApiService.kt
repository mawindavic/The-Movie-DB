package com.mawinda.data.remote

import com.mawinda.data.remote.model.response.MovieGenre
import com.mawinda.data.remote.model.response.TrendingMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/3/trending/all/week")
    suspend fun trending(@Query("page") page: Int): Response<TrendingMovies>

    @GET("/3/genre/movie/list")
    suspend fun genres(): Response<MovieGenre>

}