package com.luisfagundes.domain.usecase

import com.luisfagundes.domain.repository.MovieRepository

class GetMovieDetails(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(id: Int) = repository.fetchMovieDetails(id)
}

