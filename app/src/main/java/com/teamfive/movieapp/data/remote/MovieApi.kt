package com.teamfive.movieapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

//    @GET("/")
//    suspend fun getBatmanList(
//        @Query("s") searchQuery: String = "batman",
//        @Query("apikey") apiKey: String = "d9b74a39", // api key verilecek
//    ) : BatmanListDto

    @GET(".")
    suspend fun getMovieList(
        @Query("s") searchQuery: String = "batman",
        @Query("apikey") apiKey: String = "d9b74a39" // api key verilecek
    ) : Response<MovieListDto>

    // http://www.omdbapi.com/?i=tt0112462&plot=full
    @GET("/")
    suspend fun getDetailMovie(
        @Query("i") imdbID: String,
        @Query("plot") plot: String = "full",
        @Query("apikey") apiKey: String = "d9b74a39" // api key verilecek
    ) : Response<MovieDetailDto>
}