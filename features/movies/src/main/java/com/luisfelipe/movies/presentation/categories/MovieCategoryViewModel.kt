package com.luisfelipe.movies.presentation.categories

import com.luisfelipe.base.BaseViewModel
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.movies.domain.model.Movie
import com.luisfelipe.movies.domain.usecases.GetMostPopularMoviesUseCase
import com.luisfelipe.movies.domain.usecases.GetRecentMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class MovieCategoryViewModel(
    private val getMostPopularMoviesUseCase: GetMostPopularMoviesUseCase,
    private val getRecentMoviesUseCase: GetRecentMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<MovieCategoriesViewState, MovieCategoriesViewAction>() {

    override val viewState: MovieCategoriesViewState = MovieCategoriesViewState()

    override fun dispatchViewAction(viewAction: MovieCategoriesViewAction) {
        when (viewAction) {
            is MovieCategoriesViewAction.FetchMostPopularMovies -> getMostPopularMovies()
            is MovieCategoriesViewAction.FetchTop100Movies -> doNothing()
            is MovieCategoriesViewAction.FetchRecentMovies -> getRecentMovies()
        }
    }

    private fun getMostPopularMovies() {
        viewState.state.postValue(BaseViewState.State.LOADING)
        executeCoroutines(dispatcher) {
            getMostPopularMoviesUseCase.invoke().fold(
                ::onGetMostPopularMoviesSuccess, ::onGetMoviesFailure
            )
        }
    }

    private fun onGetMostPopularMoviesSuccess(movies: List<Movie>) {
        viewState.state.postValue(BaseViewState.State.SUCCESS)
        viewState.mostPopularMovies.postValue(movies)
    }

    private fun onGetMoviesFailure(exception: Exception) {
        Timber.e(exception)
        viewState.state.postValue(BaseViewState.State.ERROR)
    }

    private fun doNothing(): Unit = Unit

    private fun getRecentMovies() {
        viewState.state.postValue(BaseViewState.State.LOADING)
        executeCoroutines(dispatcher) {
            getRecentMoviesUseCase.invoke().fold(
                ::onGetRecentMoviesSuccess, ::onGetMoviesFailure
            )
        }
    }

    private fun onGetRecentMoviesSuccess(movies: List<Movie>) {
        viewState.state.postValue(BaseViewState.State.SUCCESS)
        viewState.recentlyReleaseMovies.postValue(movies)
    }
}