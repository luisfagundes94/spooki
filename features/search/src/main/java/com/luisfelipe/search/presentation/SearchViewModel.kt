package com.luisfelipe.search.presentation

import com.luisfelipe.base.BaseViewModel
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.domain.model.Movie
import com.luisfelipe.search.domain.usecase.SearchMoviesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class SearchViewModel(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<SearchViewState, SearchViewAction>() {

    override val viewState = SearchViewState()

    override fun dispatchViewAction(viewAction: SearchViewAction) {
        when (viewAction) {
            is SearchViewAction.SearchMoviesAndTvShows -> searchMovies(viewAction.query)
        }
    }

    private fun searchMovies(query: String) {
        viewState.state.postValue(BaseViewState.State.LOADING)
        executeCoroutines(dispatcher) {
            searchMoviesUseCase.invoke(query = query).fold(
                ::onSearchMoviesSuccess, ::onSearchMoviesError
             )
        }
    }

    private fun onSearchMoviesSuccess(movieList: List<Movie>) {
        viewState.state.postValue(BaseViewState.State.SUCCESS)
        viewState.movies.postValue(movieList)
    }

    private fun onSearchMoviesError(exception: Exception) {
        Timber.e(exception)
        viewState.state.postValue(BaseViewState.State.ERROR)
    }
}