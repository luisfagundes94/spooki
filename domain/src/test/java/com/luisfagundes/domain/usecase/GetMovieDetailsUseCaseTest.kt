package com.luisfagundes.domain.usecase

import com.luisfagundes.base.Response
import com.luisfagundes.domain.model.MovieDetails
import com.luisfagundes.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieDetailsUseCaseTest {

    private val repository: MovieRepository = mockk()
    private lateinit var useCase: GetMovieDetails

    @Before
    fun setUp() {
        useCase = GetMovieDetails(repository)
    }

    @Test
    fun `SHOULD delegate call to repository WHEN invoked`() = runBlocking {
        // Arrange
        val movieId = 10

        val movieDetailsResponse: Response<MovieDetails> = mockk()

        coEvery { repository.fetchMovieDetails(movieId) } returns movieDetailsResponse

        // Act
        useCase.invoke(movieId)

        // Assert
        coVerify(exactly = 1) {
            repository.fetchMovieDetails(movieId)
        }
    }

    @Test
    fun `SHOULD return success WHEN invoked`() = runBlockingTest {
        // Arrange
        val movieId = 10

        val movieDetails: MovieDetails = mockk()
        val movieDetailsResponse = Response.Success(movieDetails)

        coEvery { repository.fetchMovieDetails(movieId) } returns movieDetailsResponse

        // Act
        val result = useCase.invoke(movieId)

        // Assert
        assert(result.isSuccess)
    }

    @Test
    fun `SHOULD return error WHEN invoked`() = runBlockingTest {
        // Arrange
        val movieId = 10

        val exception: Exception = mockk()

        val movieDetailsResponse = Response.Error<MovieDetails>(exception)

        coEvery { repository.fetchMovieDetails(movieId) } returns movieDetailsResponse

        // Act
        val result = useCase.invoke(movieId)

        // Assert
        assert(result.isError)
    }
}