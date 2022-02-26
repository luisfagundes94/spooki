package com.luisfelipe.movies.di

import com.luisfelipe.movies.presentation.categories.MovieCategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val movieCategoryModule = module {
    // ViewModel
    viewModel {
        MovieCategoryViewModel(get())
    }
}