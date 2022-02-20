package com.luisfelipe.movies.presentation.categories

import com.luisfelipe.base.BaseViewModel
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.movies.domain.model.MovieCategory
import com.luisfelipe.movies.domain.usecases.GetMovieCategoryUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieCategoryViewModel(
    private val getMovieCategoryUseCase: GetMovieCategoryUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel<MovieCategoriesViewState, MovieCategoriesViewAction>() {

    override val viewState: MovieCategoriesViewState = MovieCategoriesViewState()

    override fun dispatchViewAction(viewAction: MovieCategoriesViewAction) {
        when (viewAction) {
            is MovieCategoriesViewAction.FetchMovieCategories -> getMovieCategories()
        }
    }

    private fun getMovieCategories() {
        viewState.state.postValue(BaseViewState.State.LOADING)
        executeCoroutines(dispatcher) {
            getMovieCategoryUseCase.invoke().fold(
                ::onGetMovieCategoriesSuccess, ::onGetMovieCategoriesFailure
            )
        }
    }

    private fun onGetMovieCategoriesSuccess(categories: List<MovieCategory>) {
        viewState.state.postValue(BaseViewState.State.SUCCESS)
        viewState.categories.postValue(categories)
    }

    private fun onGetMovieCategoriesFailure(exception: Exception) {
        Timber.e(exception)
        viewState.state.postValue(BaseViewState.State.ERROR)
    }
}