package com.luisfelipe.domain.di

import com.luisfelipe.domain.factory.MediaCategoryFactory
import com.luisfelipe.domain.factory.MediaCategoryFactoryImpl
import com.luisfelipe.domain.usecase.GetMovieCategoryUseCase
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
        MediaCategoryFactoryImpl(get()) as MediaCategoryFactory
    }

    factory {
        StringProviderImpl(androidContext()) as StringProvider
    }
}