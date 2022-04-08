package com.luisfagundes.movies.presentation.list

sealed class MovieListViewAction {
    object FetchMovieList: MovieListViewAction()
}