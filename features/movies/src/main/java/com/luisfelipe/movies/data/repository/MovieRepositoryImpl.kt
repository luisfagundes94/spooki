package com.luisfelipe.movies.data.repository

import com.luisfelipe.base.Response
import com.luisfelipe.movies.data.mapper.MovieMapper.mapToDomain
import com.luisfelipe.movies.data.service.MovieService
import com.luisfelipe.movies.domain.model.Movie
import com.luisfelipe.movies.domain.repository.MovieRepository
import java.util.*

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

    override suspend fun fetchMoviesSortedBy(param: String): Response<List<Movie>> {
        val response = Response.listOf {
            movieService.fetchMoviesSortedBy(sortBy = param).results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchTrendingMovies(): Response<List<Movie>> {
        val response = Response.listOf {
            movieService.fetchTrendingMovies().results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchMoviesReleasedThisYear(): Response<List<Movie>> {
        val response = Response.listOf {
            val year = Calendar.getInstance().get(Calendar.YEAR)
            movieService.fetchMoviesReleasedThisYear(primaryReleaseYear = year).results
        }
        return response.mapToDomain()
    }
}