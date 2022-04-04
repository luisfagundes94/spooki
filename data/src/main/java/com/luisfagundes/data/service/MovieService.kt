package com.luisfagundes.data.service

import com.luisfagundes.data.BuildConfig
import com.luisfagundes.data.model.CastResponse
import com.luisfagundes.data.model.MovieDetailsResponse
import com.luisfagundes.data.model.MovieResultsResponse
import com.luisfagundes.domain.enum.SortByType
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface MovieService {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("discover/movie")
    suspend fun fetchMoviesReleasedThisYear(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID,
        @Query("primary_release_year") primaryReleaseYear: Int
    ): MovieResultsResponse

    @GET("trending/all/day")
    suspend fun fetchTrendingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieDetailsResponse

    @GET("movie/{movie_id}/creditss")
    suspend fun fetchMovieCredits(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): CastResponse

    private companion object {
        const val HORROR_GENRE_ID = 27
    }
}