package com.luisfelipe.movies.data.repository

import com.luisfelipe.base.Response
import com.luisfelipe.movies.data.mapper.MovieMapper.mapToDomain
import com.luisfelipe.movies.data.service.MovieService
import com.luisfelipe.movies.domain.model.Movie
import com.luisfelipe.movies.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

    override suspend fun fetchMostPopularMovies(): Response<List<Movie>> {
        val response = Response.listOf {
            movieService.fetchMostPopularMovies().results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchRecentMovies(): Response<List<Movie>> {
        val response = Response.listOf {
            movieService.fetchRecentMovies().results
        }
        return response.mapToDomain()
    }
}