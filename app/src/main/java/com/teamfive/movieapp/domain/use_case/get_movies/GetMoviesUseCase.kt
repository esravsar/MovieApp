package com.teamfive.movieapp.domain.use_case.get_movies

import com.teamfive.movieapp.data.mappers.toMovie
import com.teamfive.movieapp.data.remote.MovieApi
import com.teamfive.movieapp.domain.model.Movie
import com.teamfive.movieapp.util.Resource
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(val movieApi: MovieApi) {

    suspend fun executeGetMovies(search: String?): Resource<List<Movie>> {
        return try {
            val response = search?.let { movieApi.getMovieList(it) }
            if (response?.isSuccessful == true) {
                response.body()?.let { movieListDto ->
                    return@let Resource.success(movieListDto.Search?.map { it.toMovie() })
                } ?: Resource.error("No movie found", null)
            } else {
                Resource.error("No movie found", null)
            }
        } catch (e: HttpException) {
            Resource.error("Error!", null)
        } catch (e: IOError) {
            Resource.error("Could not reach internet", null)
        }
    }
}