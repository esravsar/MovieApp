package com.teamfive.movieapp.data.repository

import com.teamfive.movieapp.data.remote.BatmanListDto
import com.teamfive.movieapp.data.remote.MovieDetailDto
import com.teamfive.movieapp.domain.repository.MovieRepository
import com.teamfive.movieapp.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.teamfive.movieapp.domain.use_case.get_movies.GetMoviesUseCase
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : MovieRepository {
    override suspend fun getMovies(search: String): BatmanListDto {
        return getMoviesUseCase.executeGetMovies(search)
    }

    override suspend fun getMovieDetail(imdbId: String): MovieDetailDto {
        return getMovieDetailsUseCase.executeGetMovieDetails(imdbId)
    }

}