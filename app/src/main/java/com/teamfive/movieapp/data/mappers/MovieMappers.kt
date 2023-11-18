package com.teamfive.movieapp.data.mappers

import com.teamfive.movieapp.data.remote.BatmanListDto
import com.teamfive.movieapp.data.remote.SearchDto
import com.teamfive.movieapp.domain.model.Movie

fun SearchDto.toMovie(): Movie {
    return Movie(
        Poster = Poster,
        Title = Title,
        Year = Year,
        imdbID = imdbID
    )
}