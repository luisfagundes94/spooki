package com.luisfelipe.movies.presentation.categories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.base.Response
import com.luisfelipe.common_testing.utils.CoroutinesTestRule
import com.luisfelipe.domain.model.MovieCategory
import com.luisfelipe.domain.usecase.GetMovieCategoryUseCase
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

    private val useCase: GetMovieCategoryUseCase = mockk()
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
            val response: Response<List<MovieCategory>> = mockk()
            coEvery { useCase.invoke() } returns response

            // Act
            viewModel.dispatchViewAction(MovieCategoriesViewAction.FetchMovieCategories)

            // Assert
            coVerify(exactly = 1) { useCase.invoke() }
        }
    }

    @Test
    fun `dispatchViewAction SHOULD should return success state viewAction is FetchMovieCategories`() {
        runBlockingTest {
            // Arrange
            val movieCategoryList: List<MovieCategory> = mockk()
            val response = Response.Success(movieCategoryList)

            coEvery { useCase.invoke() } returns response

            // Act
            viewModel.dispatchViewAction(MovieCategoriesViewAction.FetchMovieCategories)

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
            val response = Response.Error<List<MovieCategory>>(exception)

            coEvery { useCase.invoke() } returns response

            // Act
            viewModel.dispatchViewAction(MovieCategoriesViewAction.FetchMovieCategories)

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
            getMovieCategoryUseCase = useCase,
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