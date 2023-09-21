package com.rondi.core.domain.usecase

import com.rondi.core.data.Resource
import com.rondi.core.domain.model.Genre
import com.rondi.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>
    fun getGenres(): Flow<Resource<List<Genre>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    suspend fun updateMovie(movieId: Int, isFavorite: Boolean)
    fun isFavoriteMovie(movieId: Int): Flow<Boolean>
}