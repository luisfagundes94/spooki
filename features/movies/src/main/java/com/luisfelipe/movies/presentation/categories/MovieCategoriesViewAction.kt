package com.luisfelipe.movies.presentation.categories

sealed class MovieCategoriesViewAction {
    object FetchMostPopularMovies: MovieCategoriesViewAction()
    object FetchRecentMovies: MovieCategoriesViewAction()
    object FetchTop100Movies: MovieCategoriesViewAction()
}