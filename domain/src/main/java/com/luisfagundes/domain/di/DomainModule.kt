package com.luisfagundes.domain.di

import com.luisfagundes.domain.factory.MovieCategoryRepositoryStrategy
import com.luisfagundes.domain.factory.MovieCategoryRepositoryStrategyImpl
import com.luisfagundes.domain.usecase.GetMovieList
import com.luisfagundes.domain.usecase.GetMovieDetails
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
}