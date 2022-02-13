package com.luisfelipe.movies.presentation.categories

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisfelipe.base.BaseFragment
import com.luisfelipe.base.BaseViewState
import com.luisfelipe.extensions.dp
import com.luisfelipe.extensions.observe
import com.luisfelipe.movies.R
import com.luisfelipe.movies.databinding.FragmentMovieCategoriesBinding
import com.luisfelipe.movies.presentation.categories.adapter.MoviesAdapter
import com.luisfelipe.utils.RecyclerViewItemMargin
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieCategoriesFragment : BaseFragment<FragmentMovieCategoriesBinding>(
    successViewId = R.id.movie_categories_success_container,
    loadingViewId = R.id.movie_categories_loading_container,
    errorViewId = R.id.movie_categories_error_container,
) {

    private val viewModel: MovieCategoriesViewModel by viewModel()
    private val mostPopularMoviesAdapter: MoviesAdapter by inject()
    private val recentMoviesAdapter: MoviesAdapter by inject()

    override fun onBind() = FragmentMovieCategoriesBinding.inflate(layoutInflater)

    override fun FragmentMovieCategoriesBinding.onViewCreated() {
        setupViews()
        setupObservers()

        getMovieCategories()
    }

    private fun setupViews() {
        setupMostPopularRecyclerView()
        setupRecentlyReleaseRecyclerView()
    }

    private fun setupMostPopularRecyclerView() = with(binding.rvMostPopular) {
        setupRecyclerView()
        this.adapter = this@MovieCategoriesFragment.mostPopularMoviesAdapter
    }

    private fun setupRecentlyReleaseRecyclerView() = with(binding.rvRecentlyReleased) {
        setupRecyclerView()
        this.adapter = this@MovieCategoriesFragment.recentMoviesAdapter
    }

    private fun RecyclerView.setupRecyclerView() = with(this) {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val decoration = RecyclerViewItemMargin(DEFAULT_LEFT_ITEM_MARGIN.dp)

        setHasFixedSize(true)
        addItemDecoration(decoration)
        this.layoutManager = layoutManager
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

        observe(viewModel.viewState.mostPopularMovies) {
            mostPopularMoviesAdapter.updateMovies(this)
        }

        observe(viewModel.viewState.recentlyReleaseMovies) {
            recentMoviesAdapter.updateMovies(this)
        }
    }

    private fun getMovieCategories() {
        viewModel.dispatchViewAction(MovieCategoriesViewAction.FetchMostPopularMovies)
        viewModel.dispatchViewAction(MovieCategoriesViewAction.FetchRecentMovies)
    }

    override fun showError() = with(binding) {
        super.showError()
        movieCategoriesErrorContainer.btnTryAgain.setOnClickListener {
            getMovieCategories()
        }
    }

    private companion object {
        const val DEFAULT_LEFT_ITEM_MARGIN = 32
    }
}