package com.luisfelipe.search.presentation

import androidx.lifecycle.MutableLiveData
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.movies.domain.model.Media

class SearchViewState : BaseViewState() {
    val mediaList = MutableLiveData<List<Media>>()
}