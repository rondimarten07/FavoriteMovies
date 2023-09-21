package com.rondi.favmovies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import com.rondi.core.data.Resource
import com.rondi.core.domain.model.Genre
import com.rondi.core.domain.model.Movie
import com.rondi.core.domain.usecase.MovieUseCase
import com.rondi.core.utils.MovieMapper
import com.rondi.core.utils.ResourceMapper


class MainViewModel (private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getNowPlayingMovies(): LiveData<Resource<List<Movie>>> {
        return movieUseCase.getNowPlayingMovies().asLiveData().map {
            ResourceMapper.mapResource(it) { movies ->
                MovieMapper.mapMovieDomainToModel(movies)
            }
        }
    }

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

    fun getGenres(): LiveData<Resource<List<Genre>>> {
        return movieUseCase.getGenres().asLiveData().map {
            ResourceMapper.mapResource(it) { genres ->
                MovieMapper.mapGenreDomainToModel(genres)
            }
        }
    }
}