package com.luisfagundes.domain.usecase

import com.luisfagundes.base.Response
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.factory.MovieCategoryFactory
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.model.MovieCategory
import com.luisfagundes.domain.repository.MovieRepository
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
        getUpcomingMoviesAsync().await()

        if (error == null) return@coroutineScope getSuccessResponse()
        else return@coroutineScope getErrorResponse()
    }

    private fun getErrorResponse(): Response.Error<List<MovieCategory>> {
        categories.clear()
        return Response.Error(error ?: Exception())
    }

    private fun getSuccessResponse(): Response.Success<List<MovieCategory>> {
        val tempCategories = getTempCategories()
        return Response.Success(tempCategories)
    }

    private fun getTempCategories(): MutableList<MovieCategory> {
        val tempCategories = mutableListOf<MovieCategory>()
        tempCategories.addAll(categories)
        
        categories.clear()

        return tempCategories
    }

    private fun CoroutineScope.getPopularMoviesAsync() = async {
        repository.fetchPopularMovies().fold(
            onSuccess = { addMovieCategory(MovieCategoryType.POPULAR, it) },
            onError = { setError(it) }
        )
    }

    private fun CoroutineScope.getReleasedThisYearMoviesAsync() = async {
        repository.fetchMoviesReleasedThisYear().fold(
            onSuccess = { addMovieCategory(MovieCategoryType.RELEASED_THIS_YEAR, it) },
            onError = { setError(it) }
        )
    }

    private fun CoroutineScope.getTopRatedMoviesAsync() = async {
        repository.fetchTopRatedMovies().fold(
            onSuccess = { addMovieCategory(MovieCategoryType.TOP_RATED, it) },
            onError = { setError(it) }
        )
    }

    private fun CoroutineScope.getUpcomingMoviesAsync() = async {
        repository.fetchUpcomingMovies().fold(
            onSuccess = { addMovieCategory(MovieCategoryType.UPCOMING, it) },
            onError = { setError(it) }
        )
    }

    private fun addMovieCategory(type: MovieCategoryType, movies: List<Movie>) {
        categories.add(
            movieCategoryFactory.create(
                type = type,
                movieList = movies
            )
        )
    }

    private fun setError(exception: Exception) {
        error = exception
    }
}