package com.luisfelipe.movies.domain.repository

import com.luisfelipe.base.Response
import com.luisfelipe.movies.domain.model.Movie

interface MovieRepository {
    suspend fun fetchTopRatedMovies(): Response<List<Movie>>
    suspend fun fetchPopularMovies(): Response<List<Movie>>
    suspend fun fetchMoviesSortedBy(param: String): Response<List<Movie>>
    suspend fun fetchTrendingMovies(): Response<List<Movie>>
    suspend fun fetchMoviesReleasedThisYear(): Response<List<Movie>>
}