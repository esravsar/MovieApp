package com.teamfive.movieapp.di

import com.teamfive.movieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    }
}