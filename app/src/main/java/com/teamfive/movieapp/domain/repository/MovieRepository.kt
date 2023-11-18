package com.teamfive.movieapp.domain.repository

import com.teamfive.movieapp.data.remote.BatmanListDto
import com.teamfive.movieapp.data.remote.MovieDetailDto


interface MovieRepository {
    suspend fun getMovies(search : String) : BatmanListDto
    suspend fun getMovieDetail(imdbId : String) : MovieDetailDto
}