package com.luisfagundes.movies.presentation.list

import com.luisfagundes.domain.enum.MovieCategoryType

sealed class MovieListViewAction {
    data class FetchMovieList(val movieCategoryType: MovieCategoryType) : MovieListViewAction()
}