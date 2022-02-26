package com.luisfelipe.movies.presentation.details

import androidx.lifecycle.MutableLiveData
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.domain.model.MovieDetails

class MovieDetailsViewState : BaseViewState() {
    val movieDetails = MutableLiveData<MovieDetails>()
}