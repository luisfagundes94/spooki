package com.luisfagundes.movies.di

import com.luisfagundes.movies.presentation.list.MovieListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val movieListModule = module {
    // ViewModel
    viewModel {
        MovieListViewModel(get(), get())
    }
}