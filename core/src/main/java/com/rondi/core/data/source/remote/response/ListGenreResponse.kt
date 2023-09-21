package com.rondi.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListGenreResponse(
    @field:SerializedName("genres")
    val genres: List<GenreResponse>
)

data class GenreResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String
)