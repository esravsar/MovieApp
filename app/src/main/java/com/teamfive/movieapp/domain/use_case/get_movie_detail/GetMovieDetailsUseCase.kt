package com.teamfive.movieapp.domain.use_case.get_movie_detail

import com.teamfive.movieapp.domain.model.MovieDetail
import com.teamfive.movieapp.domain.repository.MovieRepository
import com.teamfive.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository : MovieRepository) {

    fun executeGetMovieDetails(imdbId: String) : Flow<Resource<MovieDetail>> = flow {
        try {
            emit(Resource.loading(null))
            val movieDetail = repository.getMovieDetail(imdbId = imdbId).toMovieDetail()
            emit(Resource.success(movieDetail))
        } catch (e: HttpException) {
            emit(Resource.error("Error!",null))
        } catch (e: IOError) {
            emit(Resource.error( "Could not reach internet",null))
        }
    }
}