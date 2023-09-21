package com.rondi.core.data

import com.rondi.core.data.source.local.LocalDataSource
import com.rondi.core.data.source.remote.RemoteDataSource
import com.rondi.core.data.source.remote.network.ApiResponse
import com.rondi.core.data.source.remote.response.GenreResponse
import com.rondi.core.data.source.remote.response.MovieResponse
import com.rondi.core.domain.model.Genre
import com.rondi.core.domain.model.Movie
import com.rondi.core.domain.repository.IMovieRepository
import com.rondi.core.utils.MovieMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {

    override fun getGenres(): Flow<Resource<List<Genre>>> =
        object : NetworkBoundResource<List<Genre>, List<GenreResponse>>() {
            override fun loadFromDB(): Flow<List<Genre>> {
                return localDataSource.getGenres().map {
                    MovieMapper.mapGenreEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GenreResponse>>> =
                remoteDataSource.getGenres()

            override suspend fun saveCallResult(data: List<GenreResponse>) {
                val genres = MovieMapper.mapGenreResponseToEntity(data)
                localDataSource.clearGenreCache()
                localDataSource.insertGenres(genres)
            }

            override fun shouldFetch(data: List<Genre>?): Boolean = data.isNullOrEmpty()
        }.asFlow()


    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getNowPlayingMovies().map {
                    MovieMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getNowPlayingMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val category = "now_playing"
                val movies = MovieMapper.mapMovieResponseToEntity(data, category)
                localDataSource.clearMovieCache(category)
                localDataSource.insertNowPlayingMovies(movies)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()
        }.asFlow()

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getPopularMovies().map {
                    MovieMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getPopularMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val category = "popular"
                val movies = MovieMapper.mapMovieResponseToEntity(data, category)
                localDataSource.clearMovieCache(category)
                localDataSource.insertPopularMovies(movies)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()
        }.asFlow()

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getTopRatedMovies().map {
                    MovieMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getTopRatedMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val category = "top_rated"
                val movies = MovieMapper.mapMovieResponseToEntity(data, category)
                localDataSource.clearMovieCache(category)
                localDataSource.insertTopRatedMovies(movies)
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data.isNullOrEmpty()
        }.asFlow()



    override fun isFavoriteMovie(movieId: Int): Flow<Boolean> =
        localDataSource.isFavoriteMovie(movieId)

    override fun getFavoriteMovies(): Flow<List<Movie>> =
        localDataSource.getFavoriteMovies().map {
            MovieMapper.mapMovieEntitiesToDomain(it)
        }

    override suspend fun updateMovie(movieId: Int, isFavorite: Boolean) =
        localDataSource.updateMovie(movieId, isFavorite)
}