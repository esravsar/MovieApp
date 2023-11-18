package com.teamfive.movieapp.data.mappers

import com.teamfive.movieapp.data.remote.MovieDetailDto
import com.teamfive.movieapp.domain.model.MovieDetail

fun MovieDetailDto.toMovieDetail(): MovieDetail {
    return MovieDetail(
        Actors = Actors,
        Awards = Awards,
        Country = Country,
        Director = Director,
        Genre = Genre,
        Language = Language,
        Poster = Poster,
        Rated = Rated,
        Released = Released,
        Title = Title,
        Type = Type,
        Year = Year,
        imdbRating = imdbRating
    )
}