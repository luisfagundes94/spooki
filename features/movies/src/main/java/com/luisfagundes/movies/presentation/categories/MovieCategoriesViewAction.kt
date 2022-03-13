package com.luisfagundes.movies.presentation.categories

sealed class MovieCategoriesViewAction {
    object FetchMovieCategories : MovieCategoriesViewAction()
}