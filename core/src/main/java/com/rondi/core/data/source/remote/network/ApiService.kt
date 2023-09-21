package com.rondi.core.data.source.remote.network

import com.rondi.core.data.source.remote.response.ListGenreResponse
import com.rondi.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): ListMovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): ListMovieResponse

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): ListMovieResponse

    @GET("genre/movie/list")
    suspend fun getGenres(): ListGenreResponse


}