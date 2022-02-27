package com.luisfelipe.search.di

import com.luisfelipe.search.domain.usecase.SearchMoviesUseCase
import com.luisfelipe.search.presentation.SearchMovieAdapter
import com.luisfelipe.search.presentation.SearchViewModel
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