package com.luisfelipe.movies.domain.repository

import com.luisfelipe.base.Response
import com.luisfelipe.movies.domain.model.Media

interface MovieRepository {
    suspend fun fetchTopRatedMovies(): Response<List<Media>>
    suspend fun fetchPopularMovies(): Response<List<Media>>
    suspend fun fetchMoviesSortedBy(param: String): Response<List<Media>>
    suspend fun fetchTrendingMovies(): Response<List<Media>>
    suspend fun fetchMoviesReleasedThisYear(): Response<List<Media>>
    suspend fun fetchUpcomingMovies(): Response<List<Media>>
    suspend fun searchMovies(query: String): Response<List<Media>>
}