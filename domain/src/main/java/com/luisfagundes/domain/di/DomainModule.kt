package com.luisfagundes.domain.di

import com.luisfagundes.domain.factory.MovieCategoryRepositoryStrategy
import com.luisfagundes.domain.factory.MovieCategoryRepositoryStrategyImpl
import com.luisfagundes.domain.usecase.GetMovieList
import com.luisfagundes.domain.usecase.GetMovieDetails
import com.luisfagundes.utils.StringProvider
import com.luisfagundes.utils.StringProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val domainModule = module {
    // UseCases
    factory {
        GetMovieList(get(), get())
    }

    factory {
        GetMovieDetails(get())
    }

    // Repository
    factory {
        MovieCategoryRepositoryStrategyImpl() as MovieCategoryRepositoryStrategy
    }

    // Utils
    factory {
        StringProviderImpl(androidContext()) as StringProvider
    }
}