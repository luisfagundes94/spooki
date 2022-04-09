package com.luisfagundes.search.presentation

import androidx.lifecycle.MutableLiveData
import com.luisfagundes.domain.model.Movie

class SearchViewState {
    val state = MutableLiveData<State>()
    val movies = MutableLiveData<List<Movie>>()

    enum class State {
        SUCCESS, LOADING, ERROR
    }
}