package com.luisfagundes.domain.usecase

import com.luisfagundes.base.Response
import com.luisfagundes.domain.factory.MovieCategoryFactory
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.model.MovieCategory
import com.luisfagundes.domain.repository.MovieRepository
import com.luisfagundes.domain.usecase.GetMovieCategoryUseCase
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieCategoryUseCaseTest {

    private val repository: MovieRepository = mockk()
    private val factory: MovieCategoryFactory = mockk()
    private lateinit var useCase: GetMovieCategoryUseCase

    @Before
    fun setUp() {
        useCase = GetMovieCategoryUseCase(
            movieCategoryFactory = factory,
            repository = repository
        )
    }

    @Test
    fun `SHOULD delegate call to repository WHEN invoked`() = runBlockingTest {
        // Arrange
        val response: Response<List<Movie>> = mockk()

        coEvery { response.fold(any(), any()) } returns Any()

        coEvery { repository.fetchPopularMovies() } returns response
        coEvery { repository.fetchMoviesReleasedThisYear() } returns response
        coEvery { repository.fetchTopRatedMovies() } returns response
        coEvery { repository.fetchUpcomingMovies() } returns response

        // Act
        useCase.invoke()

        // Assert
        coVerifySequence {
            repository.fetchPopularMovies()
            repository.fetchMoviesReleasedThisYear()
            repository.fetchTopRatedMovies()
            repository.fetchUpcomingMovies()
        }
    }

    @Test
    fun `SHOULD return success WHEN invoked`() = runBlockingTest {
        // Arrange
        val movies: List<Movie> = mockk()
        val response = Response.Success(movies)

        val movieCategory: MovieCategory = mockk()

        coEvery { factory.create(any(), any()) } returns movieCategory

        coEvery { repository.fetchPopularMovies() } returns response
        coEvery { repository.fetchMoviesReleasedThisYear() } returns response
        coEvery { repository.fetchTopRatedMovies() } returns response
        coEvery { repository.fetchUpcomingMovies() } returns response

        // Act
        val result = useCase.invoke()

        // Assert
        assert(result.isSuccess())
    }

    @Test
    fun `SHOULD return error WHEN invoked`() = runBlockingTest {
        // Arrange
        val exception: Exception = mockk()
        val response = Response.Error<List<Movie>>(exception)

        val movieCategory: MovieCategory = mockk()

        coEvery { factory.create(any(), any()) } returns movieCategory

        coEvery { repository.fetchPopularMovies() } returns response
        coEvery { repository.fetchMoviesReleasedThisYear() } returns response
        coEvery { repository.fetchTopRatedMovies() } returns response
        coEvery { repository.fetchUpcomingMovies() } returns response

        // Act
        val result = useCase.invoke()

        // Assert
        assert(result.isError())
    }

    @Test
    fun `SHOULD return error WHEN at least one repository call fails`() = runBlockingTest {
        // Arrange
        val exception: Exception = mockk()
        val errorResponse = Response.Error<List<Movie>>(exception)

        val movies: List<Movie> = mockk()
        val successResponse = Response.Success(movies)

        val movieCategory: MovieCategory = mockk()

        coEvery { factory.create(any(), any()) } returns movieCategory

        coEvery { repository.fetchPopularMovies() } returns errorResponse
        coEvery { repository.fetchMoviesReleasedThisYear() } returns successResponse
        coEvery { repository.fetchTopRatedMovies() } returns successResponse
        coEvery { repository.fetchUpcomingMovies() } returns successResponse

        // Act
        val result = useCase.invoke()

        // Assert
        assert(result.isError())
    }
}