package com.luisfelipe.movies.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("title") val title: String
)
