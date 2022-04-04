package com.luisfagundes.domain.usecase

import com.luisfagundes.domain.repository.MovieRepository

class GetMovieCast(private val repository: MovieRepository) {
    suspend operator fun invoke(movieId: Int) = repository.fetchMovieCast(movieId)
}