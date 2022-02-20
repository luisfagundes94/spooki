package com.luisfelipe.movies.presentation.categories

import androidx.lifecycle.MutableLiveData
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.movies.domain.model.Movie
import com.luisfelipe.movies.domain.model.MovieCategory

class MovieCategoriesViewState : BaseViewState() {
    val categories = MutableLiveData<List<MovieCategory>>()
}