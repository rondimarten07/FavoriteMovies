package com.rondi.core.domain.usecase

import com.rondi.core.data.Resource
import com.rondi.core.domain.model.Genre
import com.rondi.core.domain.model.Movie
import com.rondi.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

class MovieInteractor (private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> =
        movieRepository.getNowPlayingMovies()

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        movieRepository.getPopularMovies()

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> =
        movieRepository.getTopRatedMovies()

    override fun getGenres(): Flow<Resource<List<Genre>>> = movieRepository.getGenres()

    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override fun isFavoriteMovie(movieId: Int) = movieRepository.isFavoriteMovie(movieId)

    override suspend fun updateMovie(movieId: Int, isFavorite: Boolean) = movieRepository.updateMovie(movieId, isFavorite)
}