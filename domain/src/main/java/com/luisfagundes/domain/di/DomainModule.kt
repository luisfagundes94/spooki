package com.luisfagundes.domain.di

import com.luisfagundes.domain.factory.MovieCategoryRepositoryFactory
import com.luisfagundes.domain.factory.MovieCategoryRepositoryFactoryImpl
import com.luisfagundes.domain.usecase.GetMovieList
import com.luisfagundes.domain.usecase.GetMovieDetailsUseCase
import com.luisfagundes.utils.StringProvider
import com.luisfagundes.utils.StringProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val domainModule = module {
    factory {
        GetMovieList(get(), get())
    }

    factory {
        GetMovieDetailsUseCase(get())
    }

    factory {
        MovieCategoryRepositoryFactoryImpl() as MovieCategoryRepositoryFactory
    }

    factory {
        StringProviderImpl(androidContext()) as StringProvider
    }
}