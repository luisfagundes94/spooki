package com.luisfagundes.movies.presentation.baseCategory

import com.luisfagundes.domain.enum.MovieCategoryType

sealed class MovieCategoryViewAction {
    data class FetchMovieList(val movieCategoryType: MovieCategoryType) : MovieCategoryViewAction()
}