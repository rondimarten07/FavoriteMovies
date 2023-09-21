package com.rondi.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val backdropPath: String,
    val title: String,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val voteAverage: Float,
    val genreIds: List<Int?>,
    val isFavorite: Boolean
):Parcelable
