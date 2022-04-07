package com.luisfagundes.movies.presentation.details

import androidx.lifecycle.MutableLiveData
import com.luisfagundes.domain.model.Actor
import com.luisfagundes.domain.model.MovieDetails

class MovieDetailsViewState {
    val movie = MutableLiveData<State<MovieDetails>>()
    val cast = MutableLiveData<State<List<Actor>>>()

    sealed class State<out T> {
        object Loading: State<Nothing>()
        object Error: State<Nothing>()
        data class Success<T>(val data: T): State<T>()
    }
}