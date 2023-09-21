package com.rondi.core.data.source.remote

import android.util.Log
import com.rondi.core.data.source.remote.network.ApiResponse
import com.rondi.core.data.source.remote.network.ApiService
import com.rondi.core.data.source.remote.response.GenreResponse
import com.rondi.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource (private val apiService: ApiService) {
    suspend fun getGenres(): Flow<ApiResponse<List<GenreResponse>>> = flow {
        try {
            val response = apiService.getGenres()
            val listGenre = response.genres

            if (listGenre.isNotEmpty()) {
                emit(ApiResponse.Success(listGenre))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e(TAG, "getGenres: $e")
        }
    }.flowOn(Dispatchers.IO)


    suspend fun getNowPlayingMovies(): Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val response = apiService.getNowPlayingMovies()
            val listMovie = response.results

            if (listMovie.isNotEmpty()) {
                emit(ApiResponse.Success(listMovie))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e(TAG, "getNowPlayingMovies: $e")
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getPopularMovies(): Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val response = apiService.getPopularMovies()
            val listMovie = response.results

            if (listMovie.isNotEmpty()) {
                emit(ApiResponse.Success(listMovie))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e(TAG, "getPopularMovies: $e")
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getTopRatedMovies(): Flow<ApiResponse<List<MovieResponse>>> = flow {
        try {
            val response = apiService.getTopRatedMovies()
            val listMovie = response.results

            if (listMovie.isNotEmpty()) {
                emit(ApiResponse.Success(listMovie))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Log.e(TAG, "getTopRatedMovies: $e")
        }
    }.flowOn(Dispatchers.IO)


    companion object {
        private const val TAG = "RemoteDataSource"
    }
}