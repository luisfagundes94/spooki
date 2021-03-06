package com.luisfagundes.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("adult") val isAdult: Boolean,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Float,
    @SerializedName("poster_path") val posterUrl: String?,
    @SerializedName("revenue") val revenue: Int?,
    @SerializedName("budget") val budget: Int?,
    @SerializedName("backdrop_path") val backDropUrl: String?,
    @SerializedName("imdb_id") val imdbId: String,
    @SerializedName("status") val status: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("vote_count") val voteCount: Int,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("credits") val credits: CastResponse,
    @SerializedName("videos") val videos: VideoResponse
)