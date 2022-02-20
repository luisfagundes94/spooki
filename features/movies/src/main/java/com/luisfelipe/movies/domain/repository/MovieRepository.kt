package com.luisfelipe.movies.domain.repository

import com.luisfelipe.base.Response
import com.luisfelipe.movies.domain.model.Movie

interface MovieRepository {
    suspend fun fetchMoviesSortedBy(param: String): Response<List<Movie>>
    suspend fun fetchTrendingMovies(): Response<List<Movie>>
}