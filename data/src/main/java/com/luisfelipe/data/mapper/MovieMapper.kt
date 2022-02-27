package com.luisfelipe.data.mapper

import com.luisfelipe.base.Response
import com.luisfelipe.data.model.MovieResponse
import com.luisfelipe.domain.model.Movie
import com.luisfelipe.extensions.empty

object MovieMapper {

    private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    fun Response<List<MovieResponse>>.mapToDomain(): Response<List<Movie>> {
        return when (this) {
            is Response.Success -> this.mapToDomain()
            is Response.Error -> Response.Error(this.getError())
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