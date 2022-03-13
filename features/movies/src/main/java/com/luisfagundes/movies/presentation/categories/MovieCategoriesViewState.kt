package com.luisfagundes.movies.presentation.categories

import androidx.lifecycle.MutableLiveData
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.domain.model.MovieCategory

class MovieCategoriesViewState : BaseViewState() {
    val categories = MutableLiveData<List<MovieCategory>>()
}