package com.luisfagundes.data.service

import com.luisfagundes.data.model.MovieDetailsResponse
import com.luisfagundes.data.model.MovieResultsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovies(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("discover/movie")
    suspend fun fetchMoviesReleasedThisYear(
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID,
        @Query("primary_release_year") primaryReleaseYear: Int
    ): MovieResultsResponse

    @GET("trending/all/day")
    suspend fun fetchTrendingMovies(
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovies(
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(
        @Path("movie_id") id: Int,
        @Query("append_to_response") appendToResponse: String = APPEND_TO_RESPONSE_MOVIE_DETAILS
    ): MovieDetailsResponse

    private companion object {
        const val HORROR_GENRE_ID = 27
        const val APPEND_TO_RESPONSE_MOVIE_DETAILS = "credits,videos"
    }
}