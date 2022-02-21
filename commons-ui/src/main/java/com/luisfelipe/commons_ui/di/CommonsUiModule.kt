package com.luisfelipe.commons_ui.di

import com.luisfelipe.commons_ui.adapter.MediaCategoryAdapter
import org.koin.dsl.module

val commonsUiModule = module {
    // Adapters
    factory { MediaCategoryAdapter() }
}