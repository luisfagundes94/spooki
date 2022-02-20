package com.luisfelipe.search.presentation

sealed class SearchViewAction {
    data class SearchMoviesAndTvShows(val query: String) : SearchViewAction()
}