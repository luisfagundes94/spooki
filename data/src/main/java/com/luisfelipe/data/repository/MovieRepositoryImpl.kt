package com.luisfelipe.data.repository

import com.luisfelipe.base.Response
import com.luisfelipe.data.mapper.MovieMapper.mapToDomain
import com.luisfelipe.data.mapper.MovieDetailsMapper.mapToDomain
import com.luisfelipe.data.service.MovieService
import com.luisfelipe.domain.model.Movie
import com.luisfelipe.domain.model.MovieDetails
import com.luisfelipe.domain.repository.MovieRepository
import java.util.*

class MovieRepositoryImpl(
    private val movieService: MovieService
) : MovieRepository {

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

    override suspend fun fetchPopularMovies(): Response<List<Movie>> {
        val response = Response.listOf {
            movieService.fetchPopularMovies().results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchTopRatedMovies(): Response<List<Movie>> {
        val response = Response.listOf {
            movieService.fetchTopRatedMovies().results
        }
        return response.mapToDomain()
    }

    override suspend fun searchMovies(query: String): Response<List<Movie>> {
        val response = Response.listOf {
            movieService.searchMovies(query = query).results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchUpcomingMovies(): Response<List<Movie>> {
        val response = Response.listOf {
            movieService.fetchUpcomingMovies().results
        }
        return response.mapToDomain()
    }

    override suspend fun fetchMovieDetails(id: Int): Response<MovieDetails> {
        val response = Response.of {
            movieService.fetchMovieDetails(id = id)
        }
        return response.mapToDomain()
    }
}