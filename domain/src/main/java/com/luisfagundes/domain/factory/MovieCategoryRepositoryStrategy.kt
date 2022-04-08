package com.luisfagundes.domain.factory

import com.luisfagundes.core.Response
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.repository.MovieRepository

interface MovieCategoryRepositoryStrategy {
    suspend fun call(
        type: MovieCategoryType,
        movieRepository: MovieRepository
    ): Response<List<Movie>>
}

class MovieCategoryRepositoryStrategyImpl : MovieCategoryRepositoryStrategy {

    override suspend fun call(
        type: MovieCategoryType,
        movieRepository: MovieRepository
    ) = when (type) {
        MovieCategoryType.POPULAR -> movieRepository.fetchPopularMovies()
        MovieCategoryType.TOP_RATED -> movieRepository.fetchTopRatedMovies()
        MovieCategoryType.UPCOMING -> movieRepository.fetchUpcomingMovies()
        MovieCategoryType.RELEASED_THIS_YEAR -> movieRepository.fetchMoviesReleasedThisYear()
        else -> movieRepository.fetchPopularMovies()
    }
}