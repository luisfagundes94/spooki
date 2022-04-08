package com.luisfagundes.movies.presentation.list

import androidx.lifecycle.MutableLiveData
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.movies.R

class MovieListViewState : BaseViewState() {
    val movies = MutableLiveData<List<Movie>>()
    val movieType = MutableLiveData<MovieCategoryType>(MovieCategoryType.POPULAR)
}