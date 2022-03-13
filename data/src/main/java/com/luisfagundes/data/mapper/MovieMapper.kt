package com.luisfagundes.data.mapper

import com.luisfagundes.base.Response
import com.luisfagundes.data.model.MovieResponse
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.extensions.empty

object MovieMapper {

    private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    fun Response<List<MovieResponse>>.mapToDomain(): Response<List<Movie>> {
        return when (this) {
            is Response.Success -> mapToDomain()
            is Response.Error -> Response.Error(getError())
        }
    }

    private fun Response.Success<List<MovieResponse>>.mapToDomain(): Response<List<Movie>> {
        return Response.Success(this.getValue().map { it.toDomain() })
    }

    private fun MovieResponse.toDomain(): Movie {
        return Movie(
            id = this.id,
            imageUrl = BASE_IMAGE_URL + this.imageUrl,
            title = this.title ?: String.empty()
        )
    }
}