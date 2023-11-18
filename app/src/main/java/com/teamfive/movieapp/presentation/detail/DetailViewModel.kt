package com.teamfive.movieapp.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamfive.movieapp.domain.model.MovieDetail
import com.teamfive.movieapp.domain.repository.MovieRepository
import com.teamfive.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    val movie = MutableLiveData<Resource<MovieDetail>>()
    val movieLoading = MutableLiveData<Resource<Boolean>>()
    val movieError = MutableLiveData<Resource<Boolean>>()

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        movieError.value = Resource.error(throwable.localizedMessage ?: "Error!", true)
    }

    fun getMovieDetail(imdbId: String) {
        movieLoading.value = Resource.loading(true)

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val resource = movieRepository.getMovieDetail(imdbId)
            withContext(Dispatchers.Main) {
                resource.data?.let {
                    movieLoading.value = Resource.loading(false)
                    movieError.value = Resource.error("", false)
                    movie.value = resource
                }
            }
        }
    }
}