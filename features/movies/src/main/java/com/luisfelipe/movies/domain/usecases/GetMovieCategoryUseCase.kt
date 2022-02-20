package com.luisfelipe.movies.domain.usecases

import com.luisfelipe.base.Response
import com.luisfelipe.movies.domain.enums.MovieCategoryEnum
import com.luisfelipe.movies.domain.enums.SortBy
import com.luisfelipe.movies.domain.enums.titleId
import com.luisfelipe.movies.domain.model.MovieCategory
import com.luisfelipe.movies.domain.repository.MovieRepository
import com.luisfelipe.utils.StringProvider
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetMovieCategoryUseCase(
    private val stringProvider: StringProvider,
    private val repository: MovieRepository
) {
    suspend operator fun invoke() = getMovieCategories()

    private suspend fun getMovieCategories(): Response<List<MovieCategory>> = coroutineScope {
        try {
            val categories = mutableListOf<MovieCategory>()

            val deferredPopularMovies = async { repository.fetchMoviesSortedBy(SortBy.POPULARITY_DESC.value) }
            val deferredPrimaryReleased = async { repository.fetchMoviesSortedBy(SortBy.PRIMARY_RELEASE_DATE_DESC.value) }
            val deferredTrendingMovies = async { repository.fetchTrendingMovies() }

            val popularMovies = MovieCategory(
                title = stringProvider.getString(MovieCategoryEnum.POPULAR.titleId),
                movies = deferredPopularMovies.await().value()!!
            )
            categories.add(popularMovies)

            val trendingMovies = MovieCategory(
                title = stringProvider.getString(MovieCategoryEnum.TRENDING.titleId),
                movies = deferredTrendingMovies.await().value()!!
            )
            categories.add(trendingMovies)

//            val primaryReleasedMovies = MovieCategory(
//                title = stringProvider.getString(MovieCategoryEnum.PRIMARY_RELEASED.titleId),
//                movies = deferredPrimaryReleased.await().value()!!
//            )
//            categories.add(primaryReleasedMovies)

            return@coroutineScope Response.Success(categories)
        } catch (exception: Exception) {
            return@coroutineScope Response.Error(exception)
        }
    }
}