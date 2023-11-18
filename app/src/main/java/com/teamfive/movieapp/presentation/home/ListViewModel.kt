package com.teamfive.movieapp.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teamfive.movieapp.domain.model.Movie
import com.teamfive.movieapp.domain.repository.MovieRepository
import com.teamfive.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {
    val movieList = MutableLiveData<Resource<List<Movie>>>()
    val movieLoading = MutableLiveData<Resource<Boolean>>()
    val movieError = MutableLiveData<Resource<Boolean>>()

    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println(throwable.localizedMessage)
        movieError.value = Resource.error(throwable.localizedMessage ?: "Error!", true)
    }

    fun loadData(value: String) {
        movieLoading.value = Resource.loading(true)

        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val resource = movieRepository.getMovies(value)
            withContext(Dispatchers.Main) {
                if (resource.data.isNullOrEmpty()) {
                    movieError.value = Resource.error("", true)
                    movieLoading.value = Resource.loading(false)
                }
                resource.data?.let {
                    movieLoading.value = Resource.loading(false)
                    movieError.value = Resource.error("", false)
                    movieList.value = resource
                }
            }
        }
    }
}