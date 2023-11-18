package com.teamfive.movieapp.data.remote

data class BatmanListDto(
    val Response: String,
    val SearchDto: List<SearchDto>,
    val totalResults: String
)