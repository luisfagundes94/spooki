package com.luisfagundes.movies.presentation.categories

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.commons_ui.adapter.MovieCategoryAdapter
import com.luisfagundes.extensions.observe
import com.luisfagundes.movies.R
import com.luisfagundes.movies.databinding.FragmentMovieCategoryBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MovieCategoryFragment : BaseFragment<FragmentMovieCategoryBinding>(
    successViewId = R.id.movie_categories_success_container,
    loadingViewId = R.id.movie_categories_loading_container,
    errorViewId = R.id.movie_categories_error_container,
) {
    private val viewModel: MovieCategoryViewModel by viewModel()
    private val movieCategoryAdapter by inject<MovieCategoryAdapter> {
        parametersOf({ id: Int -> navigateToMovieDetails(id) })
    }

    override fun onBind() = FragmentMovieCategoryBinding.inflate(layoutInflater)

    override fun FragmentMovieCategoryBinding.onViewCreated() {
        setupViews()
        setupObservers()

        viewModel.dispatchViewAction(MovieCategoriesViewAction.FetchMovieCategories)
    }

    override fun showError() = with(binding.movieCategoriesErrorContainer) {
        super.showError()
        btnTryAgain.setOnClickListener {
            viewModel.dispatchViewAction(MovieCategoriesViewAction.FetchMovieCategories)
        }
    }

    private fun setupViews() {
        setupMovieCategoryRecyclerView()
    }

    private fun setupMovieCategoryRecyclerView() = with(binding.rvMovieCategory) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        setHasFixedSize(true)
        this.layoutManager = layoutManager
        this.adapter = movieCategoryAdapter
    }

    private fun setupObservers() {
        observe(viewModel.viewState.state) {
            when (this) {
                BaseViewState.State.SUCCESS -> showSuccess()
                BaseViewState.State.LOADING -> showLoading()
                BaseViewState.State.ERROR -> showError()
                else -> showError()
            }
        }

        observe(viewModel.viewState.categories) {
            movieCategoryAdapter.updateCategories(this)
        }
    }

    private fun navigateToMovieDetails(id: Int) {
        val destination = MovieCategoryFragmentDirections
            .actionMovieCategoryFragmentToMovieDetailsFragment(id)
        findNavController().navigate(destination)
    }
}