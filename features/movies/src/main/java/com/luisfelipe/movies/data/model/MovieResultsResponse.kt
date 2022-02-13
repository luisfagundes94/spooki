package com.luisfelipe.movies.data.model

import com.google.gson.annotations.SerializedName

data class MovieResultsResponse(
    @SerializedName("results") val results: List<MovieResponse>
)