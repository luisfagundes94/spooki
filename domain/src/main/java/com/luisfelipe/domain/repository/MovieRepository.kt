package com.luisfelipe.domain.repository

import com.luisfelipe.base.Response
import com.luisfelipe.domain.model.Movie
import com.luisfelipe.domain.model.MovieDetails

interface MovieRepository {
    suspend fun fetchTopRatedMovies(): Response<List<Movie>>
    suspend fun fetchPopularMovies(): Response<List<Movie>>
    suspend fun fetchTrendingMovies(): Response<List<Movie>>
    suspend fun fetchMoviesReleasedThisYear(): Response<List<Movie>>
    suspend fun fetchUpcomingMovies(): Response<List<Movie>>
    suspend fun searchMovies(query: String): Response<List<Movie>>
    suspend fun fetchMovieDetails(id: Int): Response<MovieDetails>
}