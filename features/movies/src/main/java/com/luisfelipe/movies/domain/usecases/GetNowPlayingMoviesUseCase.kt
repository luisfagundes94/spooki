package com.luisfelipe.movies.domain.usecases

import com.luisfelipe.movies.domain.repository.MovieRepository

class GetNowPlayingMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.fetchRecentMovies()
}