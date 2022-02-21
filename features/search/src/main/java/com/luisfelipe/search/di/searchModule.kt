package com.luisfelipe.search.di

import com.luisfelipe.search.domain.usecase.SearchMoviesAndTvShowsUseCase
import com.luisfelipe.search.presentation.MediaAdapter
import com.luisfelipe.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val searchModule = module {

    // Adapters
    factory { MediaAdapter() }

    // ViewModels
    viewModel { SearchViewModel(get()) }

    // Usecases
    factory { SearchMoviesAndTvShowsUseCase(get()) }
}