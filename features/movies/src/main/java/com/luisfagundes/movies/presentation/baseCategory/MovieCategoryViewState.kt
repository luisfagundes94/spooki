package com.luisfagundes.movies.presentation.baseCategory

import androidx.lifecycle.MutableLiveData
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.model.MovieCategory

class MovieCategoryViewState : BaseViewState() {
    val movies = MutableLiveData<List<Movie>>()
}