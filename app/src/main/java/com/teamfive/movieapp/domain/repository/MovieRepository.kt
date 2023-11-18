package com.teamfive.movieapp.domain.repository

import com.teamfive.movieapp.domain.model.Movie
import com.teamfive.movieapp.domain.model.MovieDetail
import com.teamfive.movieapp.util.Resource


interface MovieRepository {
    suspend fun getMovies(search: String?): Resource<List<Movie>>
    suspend fun getMovieDetail(imdbId: String): Resource<MovieDetail>
}