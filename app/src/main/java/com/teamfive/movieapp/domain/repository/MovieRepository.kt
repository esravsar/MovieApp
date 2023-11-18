package com.teamfive.movieapp.domain.repository

import com.teamfive.movieapp.data.remote.dto.MovieDetailDto
import com.teamfive.movieapp.data.remote.dto.BatmanListDto

interface MovieRepository {
    suspend fun getMovies(search : String) : BatmanListDto
    suspend fun getMovieDetail(imdbId : String) : MovieDetailDto
}