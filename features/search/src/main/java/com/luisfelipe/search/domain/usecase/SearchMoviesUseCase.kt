package com.luisfelipe.search.domain.usecase

import com.luisfelipe.base.Response
import com.luisfelipe.domain.model.Movie
import com.luisfelipe.domain.repository.MovieRepository

class SearchMoviesUseCase(
    private val movieRepository: MovieRepository,
    //private val tvShowRepository: TvShowRepository,
) {
    suspend operator fun invoke(query: String): Response<List<Movie>> {
        if (query.isBlank()) return Response.Success(emptyList())
        return movieRepository.searchMovies(query)
    }
}