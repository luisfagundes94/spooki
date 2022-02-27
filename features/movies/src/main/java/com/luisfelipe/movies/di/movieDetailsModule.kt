package com.luisfelipe.movies.di

import com.luisfelipe.movies.presentation.details.MovieDetailsViewModel
import com.luisfelipe.movies.presentation.details.adapter.CastAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {

    // Adapters
    factory { CastAdapter() }

    // ViewModels
    viewModel {
        MovieDetailsViewModel(get())
    }
}