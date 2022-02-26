package com.luisfelipe.commons_ui.di

import com.luisfelipe.commons_ui.adapter.MovieCategoryAdapter
import org.koin.dsl.module

val commonsUiModule = module {
    // Adapters
    factory { (navigateToMovieDetails: ((id: Int) -> Unit)) ->
        MovieCategoryAdapter(navigateToMovieDetails)
    }
}