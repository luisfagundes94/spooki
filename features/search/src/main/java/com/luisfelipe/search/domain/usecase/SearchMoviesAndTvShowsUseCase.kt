package com.luisfelipe.search.domain.usecase

import com.luisfelipe.base.Response
import com.luisfelipe.domain.model.Media
import com.luisfelipe.domain.repository.MovieRepository

class SearchMoviesAndTvShowsUseCase(
    private val movieRepository: com.luisfelipe.domain.repository.MovieRepository,
    //private val tvShowRepository: TvShowRepository,
) {
    suspend operator fun invoke(query: String): Response<List<com.luisfelipe.domain.model.Media>> {
        if (query.isBlank()) return Response.Success(emptyList())
        return movieRepository.searchMovies(query)
    }
}