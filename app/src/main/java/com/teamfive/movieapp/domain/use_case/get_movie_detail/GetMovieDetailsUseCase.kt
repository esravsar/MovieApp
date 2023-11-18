package com.teamfive.movieapp.domain.use_case.get_movie_detail

import com.teamfive.movieapp.data.mappers.toMovieDetail
import com.teamfive.movieapp.data.remote.MovieApi
import com.teamfive.movieapp.domain.model.MovieDetail
import com.teamfive.movieapp.domain.repository.MovieRepository
import com.teamfive.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val movieApi : MovieApi) {

    suspend fun executeGetMovieDetails(imdbId: String) : Resource<MovieDetail> {
        return try{
            val response=movieApi.getDetailMovie(imdbId)
            if (response.isSuccessful) {
                response.body()?.let{
                    return@let Resource.success(it.toMovieDetail())
                } ?:Resource.error("Error",null)
            }else {
                Resource.error("Response error",null)
            }
        } catch (e: IOError) {
            Resource.error( "Could not reach internet",null)
        }
    }
}