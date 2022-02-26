package com.luisfelipe.movies.presentation.categories

import androidx.lifecycle.MutableLiveData
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.domain.model.MovieCategory

class MovieCategoriesViewState : BaseViewState() {
    val categories = MutableLiveData<List<MovieCategory>>()
}