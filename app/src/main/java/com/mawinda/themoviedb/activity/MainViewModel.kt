package com.mawinda.themoviedb.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mawinda.data.domain.MovieDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MovieDBRepository) : ViewModel() {

    private val _title: MutableLiveData<String> = MutableLiveData()
    val title: LiveData<String>
        get() = _title

    init {
        loadGenre()
    }

    private fun loadGenre() {
        viewModelScope.launch {
            repository.loadGenres()
        }
    }

    fun setTitle(title: String) {
        _title.value = title
    }
}