package com.luisfelipe.spooki

import android.app.Application
import com.luisfelipe.commons_ui.di.commonsUiModule
import com.luisfelipe.data.di.dataModule
import com.luisfelipe.domain.di.domainModule
import com.luisfelipe.movies.di.movieCategoryModule
import com.luisfelipe.movies.di.movieDetailsModule
import com.luisfelipe.search.di.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import org.koin.core.logger.Logger
import timber.log.Timber


class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
        setupTimber()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@Application)
            logger(setupDependencyInjectionLogger())
            modules(
                dataModule,
                domainModule,
                movieCategoryModule,
                movieDetailsModule,
                searchModule,
                commonsUiModule
            )
        }
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupDependencyInjectionLogger(): Logger =
        if (org.koin.android.BuildConfig.DEBUG) AndroidLogger() else EmptyLogger()
}