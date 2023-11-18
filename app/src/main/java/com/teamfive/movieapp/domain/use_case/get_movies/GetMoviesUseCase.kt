package com.teamfive.movieapp.domain.use_case.get_movies

import com.teamfive.movieapp.domain.model.Movie
import com.teamfive.movieapp.domain.repository.MovieRepository
import com.teamfive.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository : MovieRepository) {

    fun executeGetMovies(search: String) : Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.loading(null))
            val movieList = repository.getMovies(search)
            if(movieList.Response.equals("True")) {
                emit(Resource.success(movieList.toMovieList()))
            } else {
                emit(Resource.error("No movie found",null))
            }
        } catch (e: HttpException) {
            emit(Resource.error("Error!",null))
        } catch (e: IOError) {
            emit(Resource.error( "Could not reach internet",null))
        }
    }
}