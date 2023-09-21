package com.rondi.core.utils

import com.rondi.core.data.source.local.entity.GenreEntity
import com.rondi.core.data.source.local.entity.MovieEntity
import com.rondi.core.data.source.remote.response.GenreResponse
import com.rondi.core.data.source.remote.response.MovieResponse
import com.rondi.core.domain.model.Genre
import com.rondi.core.domain.model.Movie

object MovieMapper {
    fun mapGenreIdToGenre(id: List<Int?>): List<String?> =
        id.map { genre ->
            when (genre) {
                28 -> "Action"
                12 -> "Adventure"
                16 -> "Animation"
                35 -> "Comedy"
                80 -> "Crime"
                99 -> "Documentary"
                18 -> "Drama"
                10751 -> "Family"
                14 -> "Fantasy"
                36 -> "History"
                27 -> "Horror"
                10402 -> "Music"
                9648 -> "Mystery"
                10749 -> "Romance"
                878 -> "Science Fiction"
                10770 -> "TV Movie"
                53 -> "Thriller"
                10752 -> "War"
                37 -> "Western"
                else -> null
            }
        }


    fun mapMovieEntitiesToDomain(movieEntities: List<MovieEntity>): List<Movie> {
        val movieList = ArrayList<Movie>()
        movieEntities.map {
            movieList.add(
                Movie(
                    id = it.id,
                    backdropPath = it.backdropPath,
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    genreIds = it.genreIds,
                    isFavorite = it.isFavorite
                )
            )
        }
        return movieList
    }

    fun mapGenreEntitiesToDomain(genreEntities: List<GenreEntity>): List<Genre> {
        val genreList = ArrayList<Genre>()
        genreEntities.map {
            genreList.add(
                Genre(
                    id = it.id,
                    name = it.name
                )
            )
        }
        return genreList
    }

    fun mapMovieResponseToEntity(
        movieResponses: List<MovieResponse>,
        category: String
    ): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        movieResponses.map {
            movieList.add(
                MovieEntity(
                    id = it.id,
                    category = category,
                    backdropPath = it.backdropPath,
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    genreIds = it.genreIds,
                    false
                )
            )
        }
        return movieList
    }

    fun mapGenreResponseToEntity(genreResponses: List<GenreResponse>): List<GenreEntity> {
        val genreList = ArrayList<GenreEntity>()
        genreResponses.map {
            genreList.add(
                GenreEntity(
                    id = it.id,
                    name = it.name
                )
            )
        }
        return genreList
    }

    fun mapMovieDomainToModel(listMovie: List<Movie>): List<Movie> {
        val movieList = ArrayList<Movie>()
        listMovie.map {
            movieList.add(
                Movie(
                    id = it.id,
                    backdropPath = it.backdropPath,
                    title = it.title,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    genreIds = it.genreIds,
                    isFavorite = it.isFavorite
                )
            )
        }
        return movieList
    }

    fun mapGenreDomainToModel(listGenre: List<Genre>): List<Genre> {
        val genreList = ArrayList<Genre>()
        listGenre.map {
            genreList.add(
                Genre(
                    id = it.id,
                    name = it.name
                )
            )
        }
        return genreList
    }
}