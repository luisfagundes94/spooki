package com.luisfelipe.movies.di

import com.luisfelipe.movies.presentation.details.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {
    viewModel {
        MovieDetailsViewModel(get())
    }
}