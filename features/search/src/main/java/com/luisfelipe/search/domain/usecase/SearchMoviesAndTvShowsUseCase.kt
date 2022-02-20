package com.luisfelipe.search.domain.usecase

import com.luisfelipe.base.Response
import com.luisfelipe.movies.domain.model.Media
import com.luisfelipe.movies.domain.repository.MovieRepository

class SearchMoviesAndTvShowsUseCase(
    private val movieRepository: MovieRepository,
    //private val tvShowRepository: TvShowRepository,
) {
    suspend operator fun invoke(query: String): Response<List<Media>> {
        if (query.isBlank()) return Response.Success(emptyList())
        return movieRepository.searchMovies(query)
    }
}