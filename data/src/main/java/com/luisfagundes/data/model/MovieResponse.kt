package com.luisfagundes.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val imageUrl: String,
    @SerializedName("title") val title: String?
)
