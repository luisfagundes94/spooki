package com.luisfagundes.search.presentation

sealed class SearchViewAction {
    data class SearchMoviesAndTvShows(val query: String) : SearchViewAction()
}