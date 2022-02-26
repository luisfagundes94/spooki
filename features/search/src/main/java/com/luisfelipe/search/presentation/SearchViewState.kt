package com.luisfelipe.search.presentation

import androidx.lifecycle.MutableLiveData
import com.luisfelipe.base.BaseViewState

class SearchViewState : BaseViewState() {
    val movies = MutableLiveData<List<com.luisfelipe.domain.model.Movie>>()
}