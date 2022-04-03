package com.luisfagundes.search.domain.usecase

import com.luisfagundes.base.Response
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.repository.MovieRepository

class SearchMoviesUseCase(
    private val movieRepository: MovieRepository,
    //private val tvShowRepository: TvShowRepository,
) {
    suspend operator fun invoke(query: String): Response<List<Movie>> {
        if (query.isBlank()) return Response.Success(emptyList())
        return movieRepository.searchMovies(query)
    }
}