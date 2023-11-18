package com.teamfive.movieapp.domain.use_case.get_movies

import com.teamfive.movieapp.data.mappers.toMovie
import com.teamfive.movieapp.data.remote.MovieApi
import com.teamfive.movieapp.domain.model.Movie
import com.teamfive.movieapp.domain.repository.MovieRepository
import com.teamfive.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val movieApi: MovieApi) {

    suspend fun executeGetMovies(search: String) : Resource<List<Movie>> {
        return try {
            val response=movieApi.getMovieList(search)
            if (response.isSuccessful){
                response.body()?.let { movieListDto->
                    return@let Resource.success(movieListDto.SearchDto.map { it.toMovie() })
                } ?: Resource.error("No movie found\"",null)
            } else {
                Resource.error("No movie found",null)
            }
        } catch (e: HttpException) {
            Resource.error("Error!",null)
        } catch (e: IOError) {
            Resource.error( "Could not reach internet",null)
        }
    }
}