package com.luisfelipe.search.di

import android.util.Log
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.luisfelipe.movies.BuildConfig
import com.luisfelipe.movies.data.repository.MovieRepositoryImpl
import com.luisfelipe.movies.data.service.MovieService
import com.luisfelipe.movies.domain.repository.MovieRepository
import com.luisfelipe.search.domain.usecase.SearchMoviesAndTvShowsUseCase
import com.luisfelipe.search.presentation.MediaAdapter
import com.luisfelipe.search.presentation.SearchViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val IMDB_MOVIES_RETROFIT = "IMDB_MOVIES_RETROFIT"

val searchModule = module {

    // Adapters
    factory { MediaAdapter() }

    // ViewModels
    viewModel { SearchViewModel(get()) }

    // Usecases
    factory { SearchMoviesAndTvShowsUseCase(get()) }

    // Repositories
    factory {
        MovieRepositoryImpl(get<MovieService>()) as MovieRepository
    }

    // Data
    factory {
        getMovieService(
            get<Retrofit>(named(IMDB_MOVIES_RETROFIT))
        )
    }

    single(named(IMDB_MOVIES_RETROFIT)) {
        createRecipeRetrofit(get<OkHttpClient>())
    }

    factory {
        createOkHttpClient()
    }
}

private fun getMovieService(retrofit: Retrofit): MovieService =
    retrofit.create(MovieService::class.java)

private fun createRecipeRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun createOkHttpClient(): OkHttpClient {
    val timeOutInSeconds = 15L
    val loggingInterceptor = LoggingInterceptor.Builder()
        .setLevel(Level.BASIC)
        .log(Log.VERBOSE)
        .build()

    return OkHttpClient.Builder()
        .connectTimeout(timeOutInSeconds, TimeUnit.SECONDS)
        .readTimeout(timeOutInSeconds, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor).build()
}