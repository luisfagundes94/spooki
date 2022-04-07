package com.luisfagundes.movies.presentation.list

import androidx.lifecycle.MutableLiveData
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.domain.model.Movie

class MovieListViewState : BaseViewState() {
    val movies = MutableLiveData<List<Movie>>()
}