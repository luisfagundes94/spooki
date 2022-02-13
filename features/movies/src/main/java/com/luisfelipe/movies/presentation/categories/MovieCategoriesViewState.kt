package com.luisfelipe.movies.presentation.categories

import androidx.lifecycle.MutableLiveData
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.movies.domain.model.Movie

class MovieCategoriesViewState : BaseViewState() {
    val mostPopularMovies = MutableLiveData<List<Movie>>()
    val recentlyReleaseMovies = MutableLiveData<List<Movie>>()
}