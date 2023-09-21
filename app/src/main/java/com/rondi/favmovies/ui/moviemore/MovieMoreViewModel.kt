package com.rondi.favmovies.ui.moviemore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.rondi.core.data.Resource
import com.rondi.core.domain.model.Movie
import com.rondi.core.domain.usecase.MovieUseCase
import com.rondi.core.utils.MovieMapper
import com.rondi.core.utils.ResourceMapper

class MovieMoreViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {
    fun getPopularMovies(): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getPopularMovies().asLiveData().map {
            ResourceMapper.mapResource(it) { movies ->
                MovieMapper.mapMovieDomainToModel(movies)
            }
        }
    }

    fun getTopRatedMovies(): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getTopRatedMovies().asLiveData().map {
            ResourceMapper.mapResource(it) { movies ->
                MovieMapper.mapMovieDomainToModel(movies)
            }
        }
    }
}