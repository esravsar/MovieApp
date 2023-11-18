package com.teamfive.movieapp.presentation.detail

import androidx.lifecycle.ViewModel
import com.teamfive.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

}