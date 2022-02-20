package com.luisfelipe.movies.presentation.categories

sealed class MovieCategoriesViewAction {
    object FetchMovieCategories : MovieCategoriesViewAction()
}