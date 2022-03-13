package com.luisfagundes.data.mapper

import com.luisfagundes.base.Response
import com.luisfagundes.data.model.MovieDetailsResponse
import com.luisfagundes.data.model.ProductionCompanyResponse
import com.luisfagundes.domain.model.MovieDetails
import com.luisfagundes.domain.model.ProductionCompany
import com.luisfagundes.extensions.empty
import com.luisfagundes.extensions.formatDate

private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/"
private const val BASE_LOGO_URL = "https://image.tmdb.org/t/p/w300/"

object MovieDetailsMapper {
    fun Response<MovieDetailsResponse>.mapToDomain(): Response<MovieDetails> {
        return when (this) {
            is Response.Success -> Response.Success(getValue().mapToDomain())
            is Response.Error -> Response.Error(getError())
        }
    }

    private fun MovieDetailsResponse.mapToDomain(): MovieDetails {
        return MovieDetails(
            id = this.id,
            title = this.title,
            posterUrl = BASE_IMAGE_URL + this.posterUrl,
            budget = this.budget ?: 0,
            revenue = this.revenue ?: 0,
            backDropUrl = BASE_IMAGE_URL + this.backDropUrl,
            overview = this.overview,
            popularity = this.popularity,
            status = this.status,
            voteAverage = this.voteAverage,
            voteCount = this.voteCount,
            releaseDate = this.releaseDate?.formatDate() ?: String.empty(),
        )
    }

    private fun List<ProductionCompanyResponse>.toDomain() =
        this.map { it.toDomain() }

    private fun ProductionCompanyResponse.toDomain() =
        ProductionCompany(
            logoUrl = BASE_LOGO_URL + this.logoPath,
            name = this.name
        )
}