package com.rondi.favmovies.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rondi.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class DetailViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {

    fun update(movieId: Int, isFavorite: Boolean) = viewModelScope.launch {
        movieUseCase.updateMovie(movieId, isFavorite)
    }

    fun isFavoriteMovie(movieId: Int) = movieUseCase.isFavoriteMovie(movieId).asLiveData()
}