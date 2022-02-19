package com.luisfelipe.movies.presentation.categories

import com.luisfelipe.base.BaseViewModel
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.movies.domain.model.Movie
import com.luisfelipe.movies.domain.usecases.GetMostPopularMoviesUseCase
import com.luisfelipe.movies.domain.usecases.GetNowPlayingMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class MovieCategoryViewModel(
    private val getMostPopularMoviesUseCase: GetMostPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<MovieCategoriesViewState, MovieCategoriesViewAction>() {

    override val viewState: MovieCategoriesViewState = MovieCategoriesViewState()

    override fun dispatchViewAction(viewAction: MovieCategoriesViewAction) {
        when (viewAction) {
            is MovieCategoriesViewAction.FetchMostPopularMovies -> getMostPopularMovies()
            is MovieCategoriesViewAction.FetchRecentMovies -> getNowPlayingMovies()
        }
    }

    private fun getMostPopularMovies() {
        executeCoroutines(dispatcher) {
            getMostPopularMoviesUseCase.invoke().fold(
                ::onGetMostPopularMoviesSuccess, ::onGetMoviesFailure
            )
        }
    }

    private fun onGetMostPopularMoviesSuccess(movies: List<Movie>) {
        viewState.mostPopularMovies.postValue(movies)
    }

    private fun onGetMoviesFailure(exception: Exception) {
        Timber.e(exception)
        viewState.state.postValue(BaseViewState.State.ERROR)
    }

    private fun getNowPlayingMovies() {
        executeCoroutines(dispatcher) {
            getNowPlayingMoviesUseCase.invoke().fold(
                ::onGetNowPlayingMoviesSucccess, ::onGetMoviesFailure
            )
        }
    }

    private fun onGetNowPlayingMoviesSucccess(movies: List<Movie>) {
        viewState.nowPlayingMovies.postValue(movies)
    }
}