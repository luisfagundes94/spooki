package com.luisfelipe.data.service

import com.luisfelipe.data.BuildConfig
import com.luisfelipe.domain.enum.SortByType
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MovieService {
    @GET("discover/movie")
    suspend fun fetchMoviesSortedBy(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("sort_by") sortBy: String? = SortByType.POPULARITY_DESC.value,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): com.luisfelipe.data.model.MovieResultsResponse

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): com.luisfelipe.data.model.MovieResultsResponse

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): com.luisfelipe.data.model.MovieResultsResponse

    @GET("discover/movie")
    suspend fun fetchMoviesReleasedThisYear(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID,
        @Query("primary_release_year") primaryReleaseYear: Int
    ): com.luisfelipe.data.model.MovieResultsResponse

    @GET("trending/all/day")
    suspend fun fetchTrendingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): com.luisfelipe.data.model.MovieResultsResponse

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): com.luisfelipe.data.model.MovieResultsResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): com.luisfelipe.data.model.MovieResultsResponse

    private companion object {
        const val HORROR_GENRE_ID = 27
    }
}