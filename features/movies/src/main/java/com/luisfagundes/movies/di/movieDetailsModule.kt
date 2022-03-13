package com.luisfagundes.movies.di

import com.luisfagundes.movies.presentation.details.MovieDetailsViewModel
import com.luisfagundes.movies.presentation.details.adapter.CastAdapter
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