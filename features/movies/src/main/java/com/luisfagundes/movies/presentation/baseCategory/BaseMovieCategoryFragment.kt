package com.luisfagundes.movies.presentation.baseCategory

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.commons_ui.adapter.MovieListAdapter
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.enum.titleId
import com.luisfagundes.extensions.dp
import com.luisfagundes.extensions.navigateWithDirections
import com.luisfagundes.extensions.observe
import com.luisfagundes.movies.MovieNavigationDirections
import com.luisfagundes.movies.R
import com.luisfagundes.movies.databinding.FragmentBaseMovieCategoryBinding
import com.luisfagundes.utils.RecyclerViewLeftItemMargin
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

abstract class BaseMovieCategoryFragment(
    private val movieCategoryType: MovieCategoryType
) : BaseFragment<FragmentBaseMovieCategoryBinding>(
    successViewId = R.id.movie_category_success_container,
    loadingViewId = R.id.movie_category_loading_container,
    errorViewId = R.id.movie_category_error_container,
) {
    private val viewModel: MovieCategoryViewModel by viewModel()
    private val movieListAdapter by inject<MovieListAdapter> {
        parametersOf({ id: Int -> navigateToMovieDetails(id) })
    }

    override fun onBind() = FragmentBaseMovieCategoryBinding.inflate(layoutInflater)

    override fun FragmentBaseMovieCategoryBinding.onViewCreated() {
        setupViews()
        setupObservers()

        viewModel.dispatchViewAction(MovieCategoryViewAction.FetchMovieList(movieCategoryType))
    }

    override fun showError() = with(binding.movieCategoryErrorContainer) {
        super.showError()
        btnTryAgain.setOnClickListener {
            viewModel.dispatchViewAction(MovieCategoryViewAction.FetchMovieList(movieCategoryType))
        }
    }

    private fun FragmentBaseMovieCategoryBinding.setupViews() {
        setupMovieCategoryTitle()
        movieCategorySuccessContainer.rvHorizontalMovies.setupHorizontalMoviesRecyclerView()
    }

    private fun FragmentBaseMovieCategoryBinding.setupMovieCategoryTitle() {
        movieCategorySuccessContainer.tvCategoryTitle.text =
            getString(movieCategoryType.titleId)
    }

    private fun RecyclerView.setupHorizontalMoviesRecyclerView() {
        val layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        setHasFixedSize(true)
        this.layoutManager = layoutManager
        this.adapter = movieListAdapter
    }

    private fun setupObservers() {
        observe(viewModel.viewState.state) {
            when (this) {
                BaseViewState.State.SUCCESS -> Unit //showSuccess()
                BaseViewState.State.LOADING -> showLoading()
                BaseViewState.State.ERROR -> showError()
                else -> showError()
            }
        }

        observe(viewModel.viewState.movies) {
            movieListAdapter.updateMovies(this)
        }
    }

    private fun navigateToMovieDetails(id: Int) {
        val destination = MovieNavigationDirections.actionToMovieDetailsFragment(id)
        findNavController().navigateWithDirections(destination)
    }
}