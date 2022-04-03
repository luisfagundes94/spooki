package com.luisfagundes.search.di

import com.luisfagundes.search.domain.usecase.SearchMoviesUseCase
import com.luisfagundes.search.presentation.SearchMovieAdapter
import com.luisfagundes.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val searchModule = module {

    // Adapters
    factory { (navigateToMovieDetails: ((id: Int) -> Unit)) ->
        SearchMovieAdapter(navigateToMovieDetails)
    }

    // ViewModels
    viewModel { SearchViewModel(get()) }

    // Usecases
    factory { SearchMoviesUseCase(get()) }
}