package com.luisfelipe.domain.usecase

import com.luisfelipe.domain.repository.MovieRepository

class GetMovieDetailsUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(id: Int) = repository.fetchMovieDetails(id)
}