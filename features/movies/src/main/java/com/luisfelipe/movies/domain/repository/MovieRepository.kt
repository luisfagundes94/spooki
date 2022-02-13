package com.luisfelipe.movies.domain.repository

import com.luisfelipe.base.Response
import com.luisfelipe.movies.domain.model.Movie

interface MovieRepository {
    suspend fun fetchMostPopularMovies(): Response<List<Movie>>
    suspend fun fetchRecentMovies(): Response<List<Movie>>
}