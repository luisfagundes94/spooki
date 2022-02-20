package com.luisfelipe.movies.data.service

import com.luisfelipe.movies.BuildConfig
import com.luisfelipe.movies.data.model.MovieResultsResponse
import com.luisfelipe.movies.domain.enums.SortBy
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("discover/movie")
    suspend fun fetchMoviesSortedBy(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int = DEFAULT_PAGE_NUMBER,
        @Query("sort_by") sortBy: String? = SortBy.POPULARITY_DESC.value,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    @GET("trending/all/day")
    suspend fun fetchTrendingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") pageNumber: Int = DEFAULT_PAGE_NUMBER,
        @Query("with_genres") withGenres: Int = HORROR_GENRE_ID
    ): MovieResultsResponse

    private companion object {
        const val HORROR_GENRE_ID = 27
        const val DEFAULT_PAGE_NUMBER = 15
    }
}