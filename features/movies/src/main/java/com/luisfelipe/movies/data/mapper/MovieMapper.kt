package com.luisfelipe.movies.data.mapper

import com.luisfelipe.base.Response
import com.luisfelipe.movies.data.model.MovieResponse
import com.luisfelipe.movies.domain.model.Movie

object MovieMapper {
    fun Response<List<MovieResponse>>.mapToDomain(): Response<List<Movie>> {
        return when (this) {
            is Response.Success -> this.mapToDomain()
            is Response.Error -> Response.Error(exception)
        }
    }

    private fun Response.Success<List<MovieResponse>>.mapToDomain(): Response<List<Movie>> {
        return Response.Success(this.data.map { it.toDomain() })
    }

    private fun MovieResponse.toDomain(): Movie {
        return Movie(
            id = this.id,
            imageUrl = this.imageUrl,
            title = this.title
        )
    }
}