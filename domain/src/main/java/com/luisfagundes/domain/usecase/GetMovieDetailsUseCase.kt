package com.luisfagundes.domain.usecase

import com.luisfagundes.base.Response
import com.luisfagundes.domain.model.Actor
import com.luisfagundes.domain.model.MovieDetails
import com.luisfagundes.domain.repository.MovieRepository
import kotlin.Exception

class GetMovieDetailsUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(id: Int): Response<MovieDetails> {
        val movieDetailsResponse = repository.fetchMovieDetails(id)
        val cast = repository.fetchMovieCast(id).getValue() ?: emptyList()

        return movieDetailsResponse.getResponseWithCast(cast)
    }

    private fun Response<MovieDetails>.getResponseWithCast(cast: List<Actor>) =
        getValue()?.let { Response.Success(getMovieDetailsWithCast(it, cast)) }
            ?: Response.Error(getError() ?: Exception())

    private fun getMovieDetailsWithCast(
        movieDetails: MovieDetails,
        cast: List<Actor>
    ) = movieDetails.copy(cast = cast)
}

