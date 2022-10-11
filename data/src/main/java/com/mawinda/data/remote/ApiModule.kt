package com.mawinda.data.remote

import com.mawinda.data.remote.interceptors.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    private const val BASE_URL = "https://api.themoviedb.org/"

    @Singleton
    @Provides
    fun providesAuthorisationInterceptor() =
        AuthInterceptor(token = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxNzRmMjgzM2IzMWYxMWE0ZDk1ODAyNzRiNmViMTgwYSIsInN1YiI6IjYzNDNmYjEzZDM0ZWIzMDA5MDM2NDIwZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WDu9IE4P6CyGGrQcorgAeESpt14LyDsogtp_EEz6iG0")

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).addInterceptor(authInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder().addConverterFactory(gsonConverterFactory).baseUrl(BASE_URL)
        .client(okHttpClient).build()

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}