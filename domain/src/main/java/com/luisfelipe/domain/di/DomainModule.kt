package com.luisfelipe.domain.di

import com.luisfelipe.domain.factory.MovieCategoryFactory
import com.luisfelipe.domain.factory.MovieCategoryFactoryImpl
import com.luisfelipe.domain.usecase.GetMovieCategoryUseCase
import com.luisfelipe.domain.usecase.GetMovieDetailsUseCase
import com.luisfelipe.utils.StringProvider
import com.luisfelipe.utils.StringProviderImpl
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