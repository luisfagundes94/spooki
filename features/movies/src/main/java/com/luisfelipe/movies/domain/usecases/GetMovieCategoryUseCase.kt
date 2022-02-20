package com.luisfelipe.movies.domain.usecases

import com.luisfelipe.base.Response
import com.luisfelipe.movies.domain.enums.MovieCategoryEnum.*
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
        getReleasedThisYearMoviesAsync().await()
        getTopRatedMoviesAsync().await()

        if (error == null) return@coroutineScope Response.Success(categories)
        else return@coroutineScope Response.Error(error ?: Exception())
    }


    private fun CoroutineScope.getPopularMoviesAsync() = async {
        repository.fetchPopularMovies()
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

    private fun CoroutineScope.getReleasedThisYearMoviesAsync() = async {
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

    private fun CoroutineScope.getTopRatedMoviesAsync() = async {
        repository.fetchTopRatedMovies()
            .fold(
                onSuccess = {
                    categories.add(
                        movieCategoryFactory.create(
                            type = TOP_RATED,
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

