package com.luisfagundes.search.presentation

import androidx.lifecycle.MutableLiveData
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.domain.model.Movie

class SearchViewState : BaseViewState() {
    val movies = MutableLiveData<List<Movie>>()
}