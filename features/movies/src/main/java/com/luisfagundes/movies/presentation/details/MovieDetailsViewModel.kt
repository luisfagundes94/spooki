package com.luisfagundes.movies.presentation.details

import com.luisfagundes.base.BaseViewModel
import com.luisfagundes.domain.model.Actor
import com.luisfagundes.domain.model.MovieDetails
import com.luisfagundes.domain.usecase.GetMovieCast
import com.luisfagundes.domain.usecase.GetMovieDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

class MovieDetailsViewModel(
    private val getMovieDetails: GetMovieDetails,
    private val getMovieCast: GetMovieCast,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<MovieDetailsViewState, MovieDetailsViewAction>() {

    override val viewState = MovieDetailsViewState()

    override fun dispatchViewAction(viewAction: MovieDetailsViewAction) {
        when (viewAction) {
            is MovieDetailsViewAction.FetchMovieDetails -> getMovieDetails(viewAction.id)
        }
    }

    private fun getMovieDetails(id: Int) {
        viewState.movie.value = MovieDetailsViewState.State.Loading
        executeCoroutines(dispatcher) {
            getMovieDetails.invoke(id).fold(
                ::onGetMovieDetailsSuccess, ::onGetMovieDetailsError
            )
        }
    }

    private fun onGetMovieDetailsSuccess(movie: MovieDetails) {
        viewState.movie.postValue(MovieDetailsViewState.State.Success(movie))
        getMovieCast(movie.id)
    }

    private fun onGetMovieDetailsError(exception: Exception) {
        Timber.e(exception.stackTraceToString())
        viewState.movie.postValue(MovieDetailsViewState.State.Error)
    }

    private fun getMovieCast(movieId: Int) {
        viewState.cast.postValue(MovieDetailsViewState.State.Loading)
        executeCoroutines(dispatcher) {
            getMovieCast.invoke(movieId).fold(
                ::onGetCastSuccess, ::onGetCastError
            )
        }
    }

    private fun onGetCastError(exception: Exception) {
        viewState.cast.postValue(MovieDetailsViewState.State.Error)
        Timber.e(exception.stackTraceToString())
    }

    private fun onGetCastSuccess(cast: List<Actor>) {
        viewState.cast.postValue(MovieDetailsViewState.State.Success(cast))
    }
}