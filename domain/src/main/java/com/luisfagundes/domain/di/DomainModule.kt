package com.luisfagundes.domain.di

import com.luisfagundes.domain.factory.MovieCategoryFactory
import com.luisfagundes.domain.factory.MovieCategoryFactoryImpl
import com.luisfagundes.domain.usecase.GetMovieCategoryUseCase
import com.luisfagundes.domain.usecase.GetMovieDetailsUseCase
import com.luisfagundes.utils.StringProvider
import com.luisfagundes.utils.StringProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val domainModule = module {
    factory {
        GetMovieCategoryUseCase(get(), get())
    }

    factory {
        GetMovieDetailsUseCase(get())
    }

    factory {
        MovieCategoryFactoryImpl(get()) as MovieCategoryFactory
    }

    factory {
        StringProviderImpl(androidContext()) as StringProvider
    }
}