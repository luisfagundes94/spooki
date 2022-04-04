package com.luisfagundes.movies.presentation.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.base.Response
import com.luisfagundes.common_testing.utils.CoroutinesTestRule
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.model.MovieCategory
import com.luisfagundes.domain.usecase.GetMovieList
import com.luisfagundes.movies.presentation.baseCategory.MovieCategoryViewAction
import com.luisfagundes.movies.presentation.baseCategory.MovieCategoryViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MovieCategoryViewModelTest {

    @get: Rule
    val instantTask = InstantTaskExecutorRule()

    @get: Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val useCase: GetMovieList = mockk()
    private lateinit var stateObserver: Observer<BaseViewState.State>
    private lateinit var viewModel: MovieCategoryViewModel

    @Before
    fun setUp() {
        setupViewModel()
        setupStateObserver()
    }

    @Test
    fun `dispatchViewAction SHOULD delegate call to useCase when viewAction is FetchMovieCategories`() {
        runBlockingTest {
            // Arrange
            val response: Response<List<Movie>> = mockk()
            val type = MovieCategoryType.POPULAR

            coEvery { useCase.invoke(type) } returns response

            // Act
            viewModel.dispatchViewAction(MovieCategoryViewAction.FetchMovieList(type))

            // Assert
            coVerify(exactly = 1) { useCase.invoke(type) }
        }
    }

    @Test
    fun `dispatchViewAction SHOULD should return success state viewAction is FetchMovieCategories`() {
        runBlockingTest {
            // Arrange
            val movieList: List<Movie> = mockk()
            val response = Response.Success(movieList)
            val type = MovieCategoryType.POPULAR

            coEvery { useCase.invoke(type) } returns response

            // Act
            viewModel.dispatchViewAction(MovieCategoryViewAction.FetchMovieList(type))

            // Assert
            verifySequence {
                stateObserver.onChanged(BaseViewState.State.LOADING)
                stateObserver.onChanged(BaseViewState.State.SUCCESS)
            }
        }
    }

    @Test
    fun `dispatchViewAction SHOULD should return error state viewAction is FetchMovieCategories`() {
        runBlockingTest {
            // Arrange
            val exception: Exception = mockk()
            val response = Response.Error<List<Movie>>(exception)
            val type = MovieCategoryType.POPULAR


            coEvery { useCase.invoke(type) } returns response

            // Act
            viewModel.dispatchViewAction(MovieCategoryViewAction.FetchMovieList(type))

            // Assert
            verifySequence {
                stateObserver.onChanged(BaseViewState.State.LOADING)
                stateObserver.onChanged(BaseViewState.State.ERROR)
            }
        }
    }

    @After
    fun tearDown() {
        removeStateObserver()
    }

    private fun setupViewModel() {
        viewModel = MovieCategoryViewModel(
            getMovieList = useCase,
            dispatcher = coroutinesTestRule.dispatcher
        )
    }

    private fun setupStateObserver() {
        stateObserver = mockk(relaxed = true)

        viewModel.viewState.state.observeForever(stateObserver)
    }

    private fun removeStateObserver() {
        viewModel.viewState.state.removeObserver(stateObserver)
    }
}