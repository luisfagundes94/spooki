package com.luisfagundes.data.mapper

import com.luisfagundes.core.Response
import com.luisfagundes.data.model.ActorResponse
import com.luisfagundes.data.model.MovieDetailsResponse
import com.luisfagundes.data.model.TrailerResponse
import com.luisfagundes.data.model.VideoResponse
import com.luisfagundes.domain.model.Actor
import com.luisfagundes.domain.model.MovieDetails
import com.luisfagundes.domain.model.Trailer
import com.luisfagundes.extensions.empty
import com.luisfagundes.extensions.formatDate

private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
private const val BASE_YOUTUBE_URL = "https://www.youtube.com/watch?v="

object MovieDetailsMapper {
    fun Response<MovieDetailsResponse>.mapToDomain(): Response<MovieDetails> {
        return when (this) {
            is Response.Success -> mapToDomain()
            is Response.Error -> Response.Error(exception)
        }
    }

    private fun Response.Success<MovieDetailsResponse>.mapToDomain(): Response<MovieDetails> {
        return Response.Success(
            MovieDetails(
                id = this.data.id,
                title = this.data.title,
                posterUrl = BASE_IMAGE_URL + this.data.posterUrl,
                budget = this.data.budget ?: 0,
                revenue = this.data.revenue ?: 0,
                backDropUrl = BASE_IMAGE_URL + this.data.backDropUrl,
                overview = this.data.overview,
                popularity = this.data.popularity,
                status = this.data.status,
                voteAverage = this.data.voteAverage,
                voteCount = this.data.voteCount,
                releaseDate = this.data.releaseDate?.formatDate() ?: String.empty(),
                cast = this.data.credits.cast.mapToDomain(),
                trailers = this.data.videos.mapToDomain()
            )
        )
    }

    private fun List<ActorResponse>.mapToDomain(): List<Actor> =
        this.map { it.toDomain() }

    private fun ActorResponse.toDomain(): Actor =
        Actor(
            profilePath = BASE_IMAGE_URL + this.profilePath
        )

    private fun VideoResponse.mapToDomain() =
        this.results.map { it.toDomain() }

    private fun TrailerResponse.toDomain() =
        Trailer(
            youtubeUrl = BASE_YOUTUBE_URL + this.key
        )
}