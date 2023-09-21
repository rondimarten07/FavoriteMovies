package com.rondi.core.data.source.local.entity

import androidx.room.Entity

@Entity(
    tableName = "movies",
    primaryKeys = ["id", "category"]
)
data class MovieEntity(
    var id: Int,
    var category: String,
    var backdropPath: String,
    var title: String,
    var overview: String,
    var posterPath: String,
    val releaseDate: String,
    var voteAverage: Float,
    val genreIds: List<Int?>,
    var isFavorite: Boolean = false
)