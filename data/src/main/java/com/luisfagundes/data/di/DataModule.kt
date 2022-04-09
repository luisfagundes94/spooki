package com.luisfagundes.data.di

import com.luisfagundes.data.BuildConfig
import com.luisfagundes.data.interceptor.AuthInterception
import com.luisfagundes.data.repository.MovieRepositoryImpl
import com.luisfagundes.data.service.MovieService
import com.luisfagundes.domain.repository.MovieRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 15L

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val dataModule =  module {
    factory {
        MovieRepositoryImpl(get<MovieService>()) as MovieRepository
    }

    factory {
        getMovieService(
            get<Retrofit>()
        )
    }

    single {
        createRecipeRetrofit(get<OkHttpClient>())
    }

    factory {
        createOkHttpClient(get<HttpLoggingInterceptor>(), get<AuthInterception>())
    }

    factory { createAuthInterception() }

    factory { createLoggingInterceptor() }
}

private fun createAuthInterception() = AuthInterception(BuildConfig.API_KEY)

private fun getMovieService(retrofit: Retrofit): MovieService =
    retrofit.create(MovieService::class.java)

private fun createRecipeRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private fun createOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    authInterception: AuthInterception
) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(authInterception)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

fun createLoggingInterceptor() = HttpLoggingInterceptor().apply {
    setLevel(
        when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
    )
}
