package com.luisfelipe.domain.usecase

import com.luisfelipe.base.Response
import com.luisfelipe.domain.factory.MediaCategoryFactory
import com.luisfelipe.domain.model.Media
import com.luisfelipe.domain.model.MediaCategory
import com.luisfelipe.domain.repository.MovieRepository
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
    private val factory: MediaCategoryFactory = mockk()
    private lateinit var useCase: GetMovieCategoryUseCase

    @Before
    fun setUp() {
        useCase = GetMovieCategoryUseCase(
            movieCategoryFactory = factory,
            repository = repository
        )
    }

    @Test
    fun `SHOULD delegate call to repository WHEN executed`() = runBlockingTest {
        // Arrange
        val response: Response<List<Media>> = mockk()

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
    fun `SHOULD return success WHEN executed`() = runBlockingTest {
        // Arrange
        val movies: List<Media> = mockk()
        val response = Response.Success(movies)

        val movieCategory: MediaCategory = mockk()

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
    fun `SHOULD return error WHEN executed`() = runBlockingTest {
        // Arrange
        val exception: Exception = mockk()
        val response = Response.Error<List<Media>>(exception)

        val movieCategory: MediaCategory = mockk()

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
        val errorResponse = Response.Error<List<Media>>(exception)

        val movies: List<Media> = mockk()
        val successResponse = Response.Success(movies)

        val movieCategory: MediaCategory = mockk()

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