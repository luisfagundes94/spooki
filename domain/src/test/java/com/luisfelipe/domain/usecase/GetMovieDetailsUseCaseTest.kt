package com.luisfelipe.domain.usecase

import com.luisfagundes.base.Response
import com.luisfagundes.domain.model.Actor
import com.luisfagundes.domain.model.MovieDetails
import com.luisfagundes.domain.repository.MovieRepository
import com.luisfagundes.domain.usecase.GetMovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieDetailsUseCaseTest {

    private val repository: MovieRepository = mockk()
    private lateinit var useCase: GetMovieDetailsUseCase

    @Before
    fun setUp() {
        useCase = GetMovieDetailsUseCase(repository)
    }

    @Test
    fun `SHOULD delegate call to repository WHEN invoked`() = runBlocking {
        // Arrange
        val movieId = 10

        val movieDetails: MovieDetails = mockk()
        val movieDetailsResponse: Response<MovieDetails> = mockk()

        val movieCast: List<Actor> = mockk()
        val movieCastResponse: Response<List<Actor>> = mockk()

        coEvery { movieCastResponse.getValue() } returns movieCast
        coEvery { movieDetailsResponse.getValue() } returns movieDetails
        coEvery { repository.fetchMovieDetails(movieId) } returns movieDetailsResponse
        coEvery { repository.fetchMovieCast(movieId) } returns movieCastResponse

        // Act
        useCase.invoke(movieId)

        // Assert
        coVerifySequence {
            repository.fetchMovieDetails(movieId)
            repository.fetchMovieCast(movieId)
        }
    }

    @Test
    fun `SHOULD return success WHEN invoked`() = runBlockingTest {
        // Arrange
        val movieId = 10

        val movieDetails: MovieDetails = mockk()
        val movieDetailsResponse = Response.Success(movieDetails)

        val movieCast: List<Actor> = mockk()
        val movieCastResponse = Response.Success(movieCast)

        coEvery { movieDetailsResponse.getValue() } returns movieDetails
        coEvery { movieCastResponse.getValue() } returns movieCast

        coEvery { repository.fetchMovieDetails(movieId) } returns movieDetailsResponse
        coEvery { repository.fetchMovieCast(movieId) } returns movieCastResponse

        // Act
        val result = useCase.invoke(movieId)

        // Assert
        assert(result.isSuccess())
    }

    @Test
    fun `SHOULD return error WHEN invoked`() = runBlockingTest {
        // Arrange
        val movieId = 10

        val exception: Exception = mockk()

        val movieDetailsResponse = Response.Error<MovieDetails>(exception)
        val movieCastResponse: Response<List<Actor>> = mockk()

        coEvery { repository.fetchMovieDetails(movieId) } returns movieDetailsResponse
        coEvery { repository.fetchMovieCast(movieId) } returns movieCastResponse

        // Act
        val result = useCase.invoke(movieId)

        // Assert
        assert(result.isError())
    }
}