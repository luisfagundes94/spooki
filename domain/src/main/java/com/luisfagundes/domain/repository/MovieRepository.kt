package com.luisfagundes.domain.repository

import com.luisfagundes.core.Response
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.model.MovieDetails

interface MovieRepository {
    suspend fun fetchTopRatedMovies(): Response<List<Movie>>
    suspend fun fetchPopularMovies(): Response<List<Movie>>
    suspend fun fetchTrendingMovies(): Response<List<Movie>>
    suspend fun fetchMoviesReleasedThisYear(): Response<List<Movie>>
    suspend fun fetchUpcomingMovies(): Response<List<Movie>>
    suspend fun searchMovies(query: String): Response<List<Movie>>
    suspend fun fetchMovieDetails(id: Int): Response<MovieDetails>
}