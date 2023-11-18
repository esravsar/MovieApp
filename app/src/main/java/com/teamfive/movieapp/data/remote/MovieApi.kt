package com.teamfive.movieapp.data.remote

import com.teamfive.movieapp.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET(".")
    suspend fun getMovieList(
        @Query("s") searchQuery: String = "batman",
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): Response<MovieListDto>

    @GET(".")
    suspend fun getDetailMovie(
        @Query("i") imdbID: String,
        @Query("plot") plot: String = "full",
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): Response<MovieDetailDto>
}