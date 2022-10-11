package com.mawinda.themoviedb.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mawinda.data.MovieDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieDBRepository) : ViewModel() {

    init {
        getTrending()
    }


    private fun getTrending() {
        viewModelScope.launch {
            repository.trendingMovies().also {
                Timber.i("Result: $it")
            }
        }
    }
}