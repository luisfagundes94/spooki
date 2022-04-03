package com.luisfagundes.domain.factory

import com.luisfagundes.base.Response
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.model.MovieCategory
import com.luisfagundes.domain.repository.MovieRepository
import com.luisfagundes.utils.StringProvider

interface MovieCategoryRepositoryFactory {
    suspend fun create(
        type: MovieCategoryType,
        movieRepository: MovieRepository
    ): Response<List<Movie>>
}

class MovieCategoryRepositoryFactoryImpl : MovieCategoryRepositoryFactory {

    override suspend fun create(
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