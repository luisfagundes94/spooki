package com.luisfelipe.search.presentation

import com.luisfelipe.base.BaseViewModel
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.domain.model.Media
import com.luisfelipe.search.domain.usecase.SearchMoviesAndTvShowsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class SearchViewModel(
    private val searchMoviesAndTvShowsUseCase: SearchMoviesAndTvShowsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<SearchViewState, SearchViewAction>() {

    override val viewState = SearchViewState()

    override fun dispatchViewAction(viewAction: SearchViewAction) {
        when (viewAction) {
            is SearchViewAction.SearchMoviesAndTvShows -> searchMoviesAndTvShows(viewAction.query)
        }
    }

    private fun searchMoviesAndTvShows(query: String) {
        viewState.state.postValue(BaseViewState.State.LOADING)
        executeCoroutines(dispatcher) {
            searchMoviesAndTvShowsUseCase.invoke(query = query).fold(
                ::onSearchMoviesAndTvShowsSuccess, ::onSearchMoviesAndTvShowsError
             )
        }
    }

    private fun onSearchMoviesAndTvShowsSuccess(mediaList: List<com.luisfelipe.domain.model.Media>) {
        viewState.state.postValue(BaseViewState.State.SUCCESS)
        viewState.mediaList.postValue(mediaList)
    }

    private fun onSearchMoviesAndTvShowsError(exception: Exception) {
        Timber.e(exception)
        viewState.state.postValue(BaseViewState.State.ERROR)
    }
}