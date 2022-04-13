package com.luisfagundes.movies.presentation.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luisfagundes.common_testing.utils.CoroutinesTestRule
import com.luisfagundes.core.Response
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.usecase.GetMovieList
import com.luisfagundes.movies.R
import com.luisfagundes.movies.presentation.list.MovieListViewAction
import com.luisfagundes.movies.presentation.list.MovieListViewModel
import com.luisfagundes.movies.presentation.list.MovieListViewState
import com.luisfagundes.movies.utils.strategy.MovieTypeStrategy
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieListViewModelTest {

    @get: Rule
    val instantTask = InstantTaskExecutorRule()

    @get: Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val useCase: GetMovieList = mockk()
    private val movieTypeStrategy: MovieTypeStrategy = mockk()
    private lateinit var stateObserver: Observer<MovieListViewState.State>
    private lateinit var checkedFilterTagObserver: Observer<MovieCategoryType>
    private lateinit var viewModel: MovieListViewModel

    @Before
    fun setUp() {
        setupViewModel()
        setupStateObserver()
    }

    @Test
    fun `dispatchViewAction SHOULD delegate call to useCase when viewAction is FetchMovieCategories`() {
        runTest {
            // Arrange
            val response: Response<List<Movie>> = mockk()
            val type = MovieCategoryType.POPULAR

            coEvery { movieTypeStrategy.getFilterType(any()) } returns type
            coEvery { useCase.invoke(type) } returns response

            // Act
            viewModel.dispatchViewAction(MovieListViewAction.FetchMovieList)

            // Assert
            coVerify(exactly = 1) { useCase.invoke(type) }
        }
    }

    @Test
    fun `dispatchViewAction SHOULD should return correct result WHEN movie type is different from default`() {
        runTest {
            // Arrange
            val movieList: List<Movie> = mockk()
            val response = Response.Success(movieList)
            val type = MovieCategoryType.TOP_RATED

            coEvery { movieTypeStrategy.getFilterType(any()) } returns type
            coEvery { useCase.invoke(type) } returns response

            // Act
            viewModel.updateCheckedFilterTag(R.id.chipTopRatedTag)
            viewModel.dispatchViewAction(MovieListViewAction.FetchMovieList)

            // Assert
            verifySequence {
                stateObserver.onChanged(MovieListViewState.State.LOADING)
                stateObserver.onChanged(MovieListViewState.State.SUCCESS)
            }
        }
    }

    @Test
    fun `dispatchViewAction SHOULD should return success state WHEN viewAction is FetchMovieCategories`() {
        runTest {
            // Arrange
            val movieList: List<Movie> = mockk()
            val response = Response.Success(movieList)
            val type = MovieCategoryType.POPULAR

            coEvery { movieTypeStrategy.getFilterType(any()) } returns type
            coEvery { useCase.invoke(type) } returns response

            // Act
            viewModel.dispatchViewAction(MovieListViewAction.FetchMovieList)

            // Assert
            verifySequence {
                stateObserver.onChanged(MovieListViewState.State.LOADING)
                stateObserver.onChanged(MovieListViewState.State.SUCCESS)
            }
        }
    }

    @Test
    fun `dispatchViewAction SHOULD should return error state WHEN viewAction is FetchMovieCategories`() {
        runTest {
            // Arrange
            val exception: Exception = mockk()
            val response = Response.Error<List<Movie>>(exception)
            val type = MovieCategoryType.POPULAR


            coEvery { movieTypeStrategy.getFilterType(any()) } returns type
            coEvery { useCase.invoke(type) } returns response

            // Act
            viewModel.dispatchViewAction(MovieListViewAction.FetchMovieList)

            // Assert
            verifySequence {
                stateObserver.onChanged(MovieListViewState.State.LOADING)
                stateObserver.onChanged(MovieListViewState.State.ERROR)
            }
        }
    }

    @Test
    fun `updateCheckedFilterTag SHOULD map live data correctly GIVEN checkedTagId`() {
        // Arrange
        val upcomingTagId = R.id.chipUpcomingTag

        every { movieTypeStrategy.getFilterType(upcomingTagId) } returns MovieCategoryType.UPCOMING

        // Act
        viewModel.updateCheckedFilterTag(upcomingTagId)

        verify {
            checkedFilterTagObserver.onChanged(MovieCategoryType.UPCOMING)
        }
    }

    @After
    fun tearDown() {
        removeStateObserver()
    }

    private fun setupViewModel() {
        viewModel = MovieListViewModel(
            getMovieList = useCase,
            movieTypeStrategy = movieTypeStrategy,
            dispatcher = coroutinesTestRule.dispatcher
        )
    }

    private fun setupStateObserver() {
        stateObserver = mockk(relaxed = true)
        checkedFilterTagObserver = mockk(relaxed = true)

        viewModel.viewState.state.observeForever(stateObserver)
        viewModel.viewState.movieType.observeForever(checkedFilterTagObserver)
    }

    private fun removeStateObserver() {
        viewModel.viewState.state.removeObserver(stateObserver)
        viewModel.viewState.movieType.removeObserver(checkedFilterTagObserver)
    }
}