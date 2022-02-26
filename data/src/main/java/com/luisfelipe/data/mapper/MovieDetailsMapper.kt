package com.luisfelipe.data.mapper

import com.luisfelipe.base.Response
import com.luisfelipe.data.model.MovieDetailsResponse
import com.luisfelipe.domain.model.MovieDetails

private const val BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w500/"

object MovieDetailsMapper {
    fun Response<MovieDetailsResponse>.mapToDomain(): Response<MovieDetails> {
        return when (this) {
            is Response.Success -> Response.Success(getValue().mapToDomain())
            is Response.Error -> Response.Error(this.getError())
        }
    }

    private fun MovieDetailsResponse.mapToDomain(): MovieDetails {
        return MovieDetails(
            id = this.id,
            title = this.title,
            posterUrl = this.posterUrl,
            budget = this.budget,
            revenue = this.revenue,
            backDropUrl = BASE_BACKDROP_URL + this.backDropUrl,
            overview = this.overview,
            popularity = this.popularity,
            status = this.status,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount
        )
    }
}