package com.luisfelipe.domain.usecase

import com.luisfelipe.base.Response
import com.luisfelipe.domain.enum.MediaCategoryType
import com.luisfelipe.domain.factory.MediaCategoryFactory
import com.luisfelipe.domain.model.Media
import com.luisfelipe.domain.model.MediaCategory
import com.luisfelipe.domain.repository.MovieRepository
import kotlinx.coroutines.*

class GetMovieCategoryUseCase(
    private val movieCategoryFactory: MediaCategoryFactory,
    private val repository: MovieRepository
) {
    private val categories = mutableListOf<MediaCategory>()
    private var error: Exception? = null

    suspend operator fun invoke() = getMovieCategories()

    private suspend fun getMovieCategories(): Response<List<MediaCategory>> = coroutineScope {

        getPopularMoviesAsync().await()
        getReleasedThisYearMoviesAsync().await()
        getTopRatedMoviesAsync().await()
        getUpcomingMoviesAsync().await()

        if (error == null) return@coroutineScope Response.Success(categories)
        else return@coroutineScope Response.Error(error ?: Exception())
    }

    private fun CoroutineScope.getPopularMoviesAsync() = async {
        repository.fetchPopularMovies()
            .fold(
                onSuccess = { addMovieCategory(MediaCategoryType.POPULAR, it)},
                onError = { setError(it) }
            )
    }

    private fun CoroutineScope.getReleasedThisYearMoviesAsync() = async {
        repository.fetchMoviesReleasedThisYear().fold(
            onSuccess = { addMovieCategory(MediaCategoryType.RELEASED_THIS_YEAR, it) },
            onError = { setError(it) }
        )
    }

    private fun CoroutineScope.getTopRatedMoviesAsync() = async {
        repository.fetchTopRatedMovies().fold(
            onSuccess = { addMovieCategory(MediaCategoryType.TOP_RATED, it) },
            onError = { setError(it) }
        )
    }

    private fun CoroutineScope.getUpcomingMoviesAsync() = async {
        repository.fetchUpcomingMovies().fold(
            onSuccess = { addMovieCategory(MediaCategoryType.UPCOMING, it) },
            onError = { setError(it) }
        )
    }

    private fun addMovieCategory(type: MediaCategoryType, movies: List<Media>) {
        categories.add(
            movieCategoryFactory.create(
                type = type,
                mediaList = movies
            )
        )
    }

    private fun setError(exception: Exception) {
        error = exception
    }
}

