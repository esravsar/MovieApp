package com.teamfive.movieapp.data.remote

data class MovieListDto(
    val Response: String,
    val Search: List<SearchDto>,
    val totalResults: String
)