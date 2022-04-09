package com.luisfagundes.movies.presentation.list

import com.luisfagundes.base.BaseViewModel
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.usecase.GetMovieList
import com.luisfagundes.movies.utils.strategy.MovieTypeStrategyImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class MovieListViewModel(
    private val getMovieList: GetMovieList,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<MovieListViewState, MovieListViewAction>() {

    override val viewState: MovieListViewState = MovieListViewState()

    override fun dispatchViewAction(viewAction: MovieListViewAction) {
        when (viewAction) {
            is MovieListViewAction.FetchMovieList -> fetchMovieList()
        }
    }

    fun updateCheckedFilterTag(id: Int) {
        viewState.movieType.value = MovieTypeStrategyImpl.getFilterType(id)
    }

    private fun fetchMovieList() {
        val movieType = getMovieType()
        viewState.state.postValue(MovieListViewState.State.LOADING)
        executeCoroutines(dispatcher) {
            getMovieList.invoke(movieType).fold(
                ::onGetListSuccess, ::onGetListFailure
            )
        }
    }

    private fun getMovieType() = viewState.movieType.value ?: MovieCategoryType.POPULAR

    private fun onGetListSuccess(movies: List<Movie>) {
        viewState.state.postValue(MovieListViewState.State.SUCCESS)
        viewState.movies.postValue(movies)
    }

    private fun onGetListFailure(exception: Exception) {
        Timber.e(exception)
        viewState.state.postValue(MovieListViewState.State.ERROR)
    }
}