package com.luisfelipe.movies.data.service

import com.luisfelipe.movies.BuildConfig
import com.luisfelipe.movies.data.model.MovieResultsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("AdvancedSearch")
    suspend fun fetchMostPopularMovies(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("title_type") titleType: String = "feature,tv_movie",
        @Query("tv_movie") tvMovies: String = "horror,thriller",
        @Query("genres") genres: String = "horror,thriller"
    ): MovieResultsResponse

    @GET("AdvancedSearch")
    suspend fun fetchRecentMovies(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("title_type") titleType: String = "feature,tv_movie",
        @Query("tv_movie") tvMovies: String = "horror,thriller",
        @Query("genres") genres: String = "horror,thriller",
        @Query("groups") sort: String = "now-playing-us"
    ): MovieResultsResponse
}