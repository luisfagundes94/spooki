package com.luisfagundes.movies.di

import com.luisfagundes.movies.presentation.details.MovieDetailsViewModel
import com.luisfagundes.movies.presentation.details.adapter.CastAdapter
import com.luisfagundes.movies.presentation.details.adapter.TrailerAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieDetailsModule = module {

    // Adapters
    factory { CastAdapter() }
    factory { TrailerAdapter() }

    // ViewModels
    viewModel {
        MovieDetailsViewModel(get())
    }
}