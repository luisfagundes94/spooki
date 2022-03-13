package com.luisfagundes.movies.di

import com.luisfagundes.movies.presentation.categories.MovieCategoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val movieCategoryModule = module {
    // ViewModel
    viewModel {
        MovieCategoryViewModel(get())
    }
}