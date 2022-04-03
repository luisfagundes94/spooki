package com.luisfagundes.domain.usecase

import com.luisfagundes.base.Response
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.factory.MovieCategoryRepositoryFactory
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.repository.MovieRepository

class GetMovieList(
    private val movieCategoryRepositoryFactory: MovieCategoryRepositoryFactory,
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movieCategoryType: MovieCategoryType): Response<List<Movie>> {
        return movieCategoryRepositoryFactory.create(
            type = movieCategoryType,
            movieRepository = repository
        )
    }
}