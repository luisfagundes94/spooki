package com.luisfelipe.movies.di

import com.luisfelipe.movies.presentation.categories.MovieCategoryViewModel
import com.luisfelipe.movies.presentation.categories.adapter.MovieCategoryAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val movieCategoryModule = module {

    // Adapters
    factory { MovieCategoryAdapter() }

    // ViewModel
    viewModel {
        MovieCategoryViewModel(get())
    }
}