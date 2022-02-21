package com.luisfelipe.domain.usecase

import com.luisfelipe.base.Response
import com.luisfelipe.domain.enum.MediaCategoryType
import com.luisfelipe.domain.factory.MediaCategoryFactory
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
                onSuccess = {
                    categories.add(
                        movieCategoryFactory.create(
                            type = MediaCategoryType.POPULAR,
                            mediaList = it
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
                            type = MediaCategoryType.RELEASED_THIS_YEAR,
                            mediaList = it
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
                            type = MediaCategoryType.TOP_RATED,
                            mediaList = it
                        )
                    )
                },
                onError = { onGetMovieListError(it) }
            )
    }

    private fun CoroutineScope.getUpcomingMoviesAsync() = async {
        repository.fetchUpcomingMovies()
            .fold(
                onSuccess = {
                    categories.add(
                        movieCategoryFactory.create(
                            type = MediaCategoryType.UPCOMING,
                            mediaList = it
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

