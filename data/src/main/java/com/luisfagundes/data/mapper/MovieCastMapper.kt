package com.luisfagundes.data.mapper

import com.luisfagundes.base.Response
import com.luisfagundes.data.model.ActorResponse
import com.luisfagundes.domain.model.Actor

object MovieCastMapper {

    private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    fun Response<List<ActorResponse>>.mapToDomain(): Response<List<Actor>> {
        return when (this) {
            is Response.Success -> mapToDomain()
            is Response.Error -> Response.Error(getError())
        }
    }

    private fun Response.Success<List<ActorResponse>>.mapToDomain() =
        Response.Success(getValue().map { it.toDomain() })

    private fun ActorResponse.toDomain() = Actor(
        profilePath = BASE_IMAGE_URL + this.profilePath
    )
}