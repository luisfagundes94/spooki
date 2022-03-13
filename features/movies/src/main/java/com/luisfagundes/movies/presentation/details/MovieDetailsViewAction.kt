package com.luisfagundes.movies.presentation.details

sealed class MovieDetailsViewAction {
    data class FetchMovieDetails(val id: Int) : MovieDetailsViewAction()
}