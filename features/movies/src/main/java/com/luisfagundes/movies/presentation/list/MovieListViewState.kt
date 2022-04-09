package com.luisfagundes.movies.presentation.list

import androidx.lifecycle.MutableLiveData
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.model.Movie

class MovieListViewState {
    val state = MutableLiveData<State>()
    val movies = MutableLiveData<List<Movie>>()
    val movieType = MutableLiveData<MovieCategoryType>(MovieCategoryType.POPULAR)

    enum class State {
        SUCCESS, LOADING, ERROR
    }
}