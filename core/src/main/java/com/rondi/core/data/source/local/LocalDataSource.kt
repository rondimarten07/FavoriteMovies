package com.rondi.core.data.source.local

import com.rondi.core.data.source.local.entity.GenreEntity
import com.rondi.core.data.source.local.entity.MovieEntity
import com.rondi.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val movieDao: MovieDao) {
    fun getNowPlayingMovies(): Flow<List<MovieEntity>> = movieDao.getNowPlayingMovies()

    fun getPopularMovies(): Flow<List<MovieEntity>> = movieDao.getPopularMovies()

    fun getTopRatedMovies(): Flow<List<MovieEntity>> = movieDao.getTopRatedMovies()

    suspend fun insertNowPlayingMovies(movies: List<MovieEntity>) =
        movieDao.insertNowPlayingMovies(movies)

    suspend fun insertPopularMovies(movies: List<MovieEntity>) =
        movieDao.insertPopularMovies(movies)

    suspend fun insertTopRatedMovies(movies: List<MovieEntity>) =
        movieDao.insertTopRatedMovies(movies)

    suspend fun clearMovieCache(category: String) = movieDao.clearMovieCache(category)

    fun getGenres(): Flow<List<GenreEntity>> = movieDao.getGenres()

    suspend fun insertGenres(genres: List<GenreEntity>) = movieDao.insertGenres(genres)

    suspend fun clearGenreCache() = movieDao.clearGenreCache()

    fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    fun isFavoriteMovie(movieId: Int) = movieDao.isFavoriteMovie(movieId)

    suspend fun updateMovie(movieId: Int, isFavorite: Boolean) = movieDao.updateMovie(movieId, isFavorite)

}