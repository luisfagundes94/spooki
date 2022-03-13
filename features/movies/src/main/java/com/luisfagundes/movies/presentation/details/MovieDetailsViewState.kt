package com.luisfagundes.movies.presentation.details

import androidx.lifecycle.MutableLiveData
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.domain.model.MovieDetails

class MovieDetailsViewState : BaseViewState() {
    val movieDetails = MutableLiveData<MovieDetails>()
}