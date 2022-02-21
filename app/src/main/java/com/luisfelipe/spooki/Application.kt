package com.luisfelipe.spooki

import android.app.Application
import com.luisfelipe.data.di.dataModule
import com.luisfelipe.domain.di.domainModule
import com.luisfelipe.movies.di.movieCategoryModule
import com.luisfelipe.search.di.searchModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@Application)
            logger(setupDependencyInjectionLogger())
            modules(
                dataModule,
                domainModule,
                movieCategoryModule,
                searchModule
            )
        }
    }

    private fun setupDependencyInjectionLogger(): Logger =
        if (BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
}