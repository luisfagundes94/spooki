package com.luisfelipe.movies.domain.usecases

import com.luisfelipe.base.Response
import com.luisfelipe.movies.domain.enums.MovieCategoryEnum.*
import com.luisfelipe.movies.domain.enums.SortBy
import com.luisfelipe.movies.domain.factory.MovieCategoryFactory
import com.luisfelipe.movies.domain.model.MovieCategory
import com.luisfelipe.movies.domain.repository.MovieRepository
import kotlinx.coroutines.*

class GetMovieCategoryUseCase(
    private val movieCategoryFactory: MovieCategoryFactory,
    private val repository: MovieRepository
) {
    private val categories = mutableListOf<MovieCategory>()
    private var error: Exception? = null

    suspend operator fun invoke() = getMovieCategories()

    private suspend fun getMovieCategories(): Response<List<MovieCategory>> = coroutineScope {

        getPopularMoviesAsync().await()
        getMoviesReleasedThisYear().await()
        getTrendingMoviesAsync().await()

        if (error == null) return@coroutineScope Response.Success(categories)
        else return@coroutineScope Response.Error(error ?: Exception())
    }


    private fun CoroutineScope.getPopularMoviesAsync() = async {
        repository.fetchMoviesSortedBy(SortBy.POPULARITY_DESC.value)
            .fold(
                onSuccess = {
                    categories.add(
                        movieCategoryFactory.create(
                            type = POPULAR,
                            movieList = it
                        )
                    )
                },
                onError = { onGetMovieListError(it) }
            )
    }

    private fun CoroutineScope.getMoviesReleasedThisYear() = async {
        repository.fetchMoviesReleasedThisYear()
            .fold(
                onSuccess = {
                    categories.add(
                        movieCategoryFactory.create(
                            type = RELEASED_THIS_YEAR,
                            movieList = it
                        )
                    )
                },
                onError = { onGetMovieListError(it) }
            )
    }

    private fun CoroutineScope.getTrendingMoviesAsync() = async {
        repository.fetchMoviesSortedBy(SortBy.PRIMARY_RELEASE_DATE_DESC.value)
            .fold(
                onSuccess = {
                    categories.add(
                        movieCategoryFactory.create(
                            type = PRIMARY_RELEASED,
                            movieList = it
                        )
                    )
                },
                onError = { onGetMovieListError(it) }
            )
    }

    private fun onGetMovieListError(exception: Exception) {
        error = exception
    }
}

