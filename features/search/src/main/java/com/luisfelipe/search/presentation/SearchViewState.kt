package com.luisfelipe.search.presentation

import androidx.lifecycle.MutableLiveData
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.domain.model.Media

class SearchViewState : BaseViewState() {
    val mediaList = MutableLiveData<List<com.luisfelipe.domain.model.Media>>()
}