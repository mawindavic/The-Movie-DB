package com.mawinda.themoviedb.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mawinda.data.domain.MovieDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieDBRepository) : ViewModel() {

    val movies = repository.movies()

    private val _error: MutableLiveData<com.mawinda.data.domain.model.Error?> = MutableLiveData()
    val error: LiveData<com.mawinda.data.domain.model.Error?>
        get() = _error

    fun setError(error: com.mawinda.data.domain.model.Error?) {
        viewModelScope.launch {
            _error.value = error
        }
    }
}