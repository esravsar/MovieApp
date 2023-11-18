package com.teamfive.movieapp.data.remote

data class MovieListDto(
    val Response: String,
    val SearchDto: List<SearchDto>,
    val totalResults: String
)