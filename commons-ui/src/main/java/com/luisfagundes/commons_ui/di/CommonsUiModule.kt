package com.luisfagundes.commons_ui.di

import com.luisfagundes.commons_ui.adapter.MovieListAdapter
import org.koin.dsl.module

val commonsUiModule = module {
    // Adapters
    factory { (navigateToMovieDetails: ((id: Int) -> Unit)) ->
        MovieListAdapter(navigateToMovieDetails)
    }
}