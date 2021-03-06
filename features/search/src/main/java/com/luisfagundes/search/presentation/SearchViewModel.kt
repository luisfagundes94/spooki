package com.luisfagundes.search.presentation

import com.luisfagundes.base.BaseViewModel
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.search.domain.usecase.SearchMoviesUseCase
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
        viewState.state.postValue(SearchViewState.State.LOADING)
        executeCoroutines(dispatcher) {
            searchMoviesUseCase.invoke(query = query).fold(
                ::onSearchMoviesSuccess, ::onSearchMoviesError
             )
        }
    }

    private fun onSearchMoviesSuccess(movieList: List<Movie>) {
        viewState.state.postValue(SearchViewState.State.SUCCESS)
        viewState.movies.postValue(movieList)
    }

    private fun onSearchMoviesError(exception: Exception) {
        Timber.e(exception)
        viewState.state.postValue(SearchViewState.State.ERROR)
    }
}