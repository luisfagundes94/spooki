package com.luisfagundes.domain.model

data class MovieDetails(
    val id: String,
    val title: String,
    val posterUrl: String?,
    val budget: Int?,
    val revenue: Int?,
    val backDropUrl: String?,
    val overview: String,
    val popularity: Float,
    val status: String,
    val voteAverage: Float,
    val voteCount: Int,
    val releaseDate: String?,
    val cast: List<Actor> = emptyList(),
)
