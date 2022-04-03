package com.luisfagundes.movies.presentation.baseCategory

import com.luisfagundes.base.BaseViewModel
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.usecase.GetMovieList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class MovieCategoryViewModel(
    private val getMovieList: GetMovieList,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<MovieCategoryViewState, MovieCategoryViewAction>() {

    override val viewState: MovieCategoryViewState = MovieCategoryViewState()

    override fun dispatchViewAction(viewAction: MovieCategoryViewAction) {
        when (viewAction) {
            is MovieCategoryViewAction.FetchMovieList -> fetchMovieList(viewAction.movieCategoryType)
        }
    }

    private fun fetchMovieList(movieCategoryType: MovieCategoryType) {
        viewState.state.postValue(BaseViewState.State.LOADING)
        executeCoroutines(dispatcher) {
            getMovieList.invoke(movieCategoryType).fold(
                ::onGetListSuccess, ::onGetListFailure
            )
        }
    }

    private fun onGetListSuccess(movies: List<Movie>) {
        viewState.state.postValue(BaseViewState.State.SUCCESS)
        viewState.movies.postValue(movies)
    }

    private fun onGetListFailure(exception: Exception) {
        Timber.e(exception)
        viewState.state.postValue(BaseViewState.State.ERROR)
    }
}