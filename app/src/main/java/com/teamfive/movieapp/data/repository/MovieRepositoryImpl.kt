package com.teamfive.movieapp.data.repository

import com.teamfive.movieapp.domain.model.Movie
import com.teamfive.movieapp.domain.model.MovieDetail
import com.teamfive.movieapp.domain.repository.MovieRepository
import com.teamfive.movieapp.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.teamfive.movieapp.domain.use_case.get_movies.GetMoviesUseCase
import com.teamfive.movieapp.util.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : MovieRepository {
    override suspend fun getMovies(search: String?): Resource<List<Movie>> {
        return getMoviesUseCase.executeGetMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): Resource<MovieDetail> {
        return getMovieDetailsUseCase.executeGetMovieDetails(imdbId)
    }
}