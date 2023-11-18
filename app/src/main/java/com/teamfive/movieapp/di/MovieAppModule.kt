package com.teamfive.movieapp.di

import com.teamfive.movieapp.data.remote.MovieApi
import com.teamfive.movieapp.data.repository.MovieRepositoryImpl
import com.teamfive.movieapp.domain.repository.MovieRepository
import com.teamfive.movieapp.domain.use_case.get_movie_detail.GetMovieDetailsUseCase
import com.teamfive.movieapp.domain.use_case.get_movies.GetMoviesUseCase
import com.teamfive.movieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

object MovieAppModule {
    @Module
    @InstallIn(SingletonComponent::class)
    object AppModule{
        @Singleton
        @Provides
        fun createRetrofit() : Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }

        @Singleton
        @Provides
        fun createMovieApi(retrofit: Retrofit): MovieApi {
            return retrofit.create(MovieApi::class.java)
        }

        @Singleton
        @Provides
        fun injectMovieRepo(getMoviesUseCase: GetMoviesUseCase,getMovieDetailsUseCase: GetMovieDetailsUseCase) = MovieRepositoryImpl(getMoviesUseCase,getMovieDetailsUseCase) as MovieRepository
    }
}