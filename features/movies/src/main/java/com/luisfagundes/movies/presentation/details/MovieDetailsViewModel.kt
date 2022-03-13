package com.luisfagundes.movies.presentation.details

import com.luisfagundes.base.BaseViewModel
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.domain.model.MovieDetails
import com.luisfagundes.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import timber.log.Timber

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<MovieDetailsViewState, MovieDetailsViewAction>() {

    override val viewState = MovieDetailsViewState()

    override fun dispatchViewAction(viewAction: MovieDetailsViewAction) {
        when (viewAction) {
            is MovieDetailsViewAction.FetchMovieDetails -> getMovieDetails(viewAction.id)
        }
    }

    private fun getMovieDetails(id: Int) {
        viewState.state.value = BaseViewState.State.LOADING
        executeCoroutines(dispatcher) {
            getMovieDetailsUseCase.invoke(id).fold(
                ::onGetMovieDetailsSuccess, ::onGetMovieDetailsError
            )
        }
    }

    private fun onGetMovieDetailsSuccess(movie: MovieDetails) {
        viewState.state.postValue(BaseViewState.State.SUCCESS)
        viewState.movieDetails.postValue(movie)
    }

    private fun onGetMovieDetailsError(exception: Exception) {
        Timber.e(exception.stackTraceToString())
        viewState.state.postValue(BaseViewState.State.ERROR)
    }
}