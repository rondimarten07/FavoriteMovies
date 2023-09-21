package com.rondi.favorite.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rondi.core.domain.model.Movie
import com.rondi.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel (private val movieUseCase: MovieUseCase): ViewModel() {

    fun getFavoriteMovies(): LiveData<List<Movie>> {
        return movieUseCase.getFavoriteMovies().asLiveData()
    }

}