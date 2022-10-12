package com.mawinda.themoviedb.fragments.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mawinda.data.domain.MovieDBRepository
import com.mawinda.data.local.entities.Genre
import com.mawinda.data.local.entities.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: MovieDBRepository) :
    ViewModel() {

    private val _movie: MutableLiveData<Movie> = MutableLiveData()
    val movie: LiveData<Movie>
        get() = _movie


    private val _genres: MutableLiveData<List<Genre>> = MutableLiveData()
    val genres: LiveData<List<Genre>>
        get() = _genres

    fun setMovie(movie: Movie) {
        _movie.value = movie
        loadGenres(movie)
    }

    private fun loadGenres(movie: Movie) {
        viewModelScope.launch {
            _genres.value = repository.genres(movie)
        }
    }

}