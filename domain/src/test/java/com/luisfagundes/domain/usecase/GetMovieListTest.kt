package com.luisfagundes.domain.usecase

import com.luisfagundes.base.Response
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.factory.MovieCategoryRepositoryFactory
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.model.MovieCategory
import com.luisfagundes.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieListTest {

    private val repository: MovieRepository = mockk()
    private val repositoryFactory: MovieCategoryRepositoryFactory = mockk()
    private lateinit var useCase: GetMovieList

    @Before
    fun setUp() {
        useCase = GetMovieList(
            movieCategoryRepositoryFactory = repositoryFactory,
            repository = repository
        )
    }

    @Test
    fun `SHOULD delegate call to repository WHEN invoked`() = runBlockingTest {
        // Arrange
        val response: Response<List<Movie>> = mockk()

        coEvery { repository.fetchPopularMovies() } returns response

        // Act
        useCase.invoke(MovieCategoryType.POPULAR)

        // Assert
        coVerify(exactly = 1) {
            repository.fetchPopularMovies()
        }
    }

    @Test
    fun `SHOULD return success WHEN invoked`() = runBlockingTest {
        // Arrange
        val movies: List<Movie> = mockk()
        val response = Response.Success(movies)

        coEvery { repository.fetchPopularMovies() } returns response
        // Act
        val result = useCase.invoke(MovieCategoryType.POPULAR)

        // Assert
        assert(result.isSuccess())
    }

    @Test
    fun `SHOULD return error WHEN invoked`() = runBlockingTest {
        // Arrange
        val exception: Exception = mockk()
        val response = Response.Error<List<Movie>>(exception)

        coEvery { repository.fetchPopularMovies() } returns response

        // Act
        val result = useCase.invoke(MovieCategoryType.POPULAR)

        // Assert
        assert(result.isError())
    }
}