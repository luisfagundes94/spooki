package com.luisfelipe.movies.data.repository

import com.luisfelipe.base.Response
import com.luisfelipe.movies.data.mapper.MovieMapper.mapToDomain
import com.luisfelipe.movies.data.service.MovieService
import com.luisfelipe.movies.domain.model.Media
import com.luisfelipe.movies.domain.repository.MovieRepository
import java.util.*

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

    override suspend fun fetchMoviesSortedBy(param: String): Response<List<Media>> {
        val response = Response.listOf {
            movieService.fetchMoviesSortedBy(sortBy = param).results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchTrendingMovies(): Response<List<Media>> {
        val response = Response.listOf {
            movieService.fetchTrendingMovies().results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchMoviesReleasedThisYear(): Response<List<Media>> {
        val response = Response.listOf {
            val year = Calendar.getInstance().get(Calendar.YEAR)
            movieService.fetchMoviesReleasedThisYear(primaryReleaseYear = year).results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchPopularMovies(): Response<List<Media>> {
        val response = Response.listOf {
            movieService.fetchPopularMovies().results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchTopRatedMovies(): Response<List<Media>> {
        val response = Response.listOf {
            movieService.fetchTopRatedMovies().results
        }
        return response.mapToDomain()
    }

    override suspend fun searchMovies(query: String): Response<List<Media>> {
        val response = Response.listOf {
            movieService.searchMovies(query = query).results
        }
        return response.mapToDomain()
    }
}