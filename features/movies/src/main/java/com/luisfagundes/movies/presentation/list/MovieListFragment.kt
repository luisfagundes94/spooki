package com.luisfagundes.movies.presentation.list

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.commons_ui.adapter.MovieListAdapter
import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.extensions.*
import com.luisfagundes.movies.MovieNavigationDirections
import com.luisfagundes.movies.R
import com.luisfagundes.movies.databinding.FragmentMovieListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber


class MovieListFragment : BaseFragment<FragmentMovieListBinding>(
    successViewId = R.id.movie_list_success_container,
    loadingViewId = R.id.movie_list_loading_container,
    errorViewId = R.id.movie_list_error_container
) {

    private val viewModel: MovieListViewModel by viewModel()
    private val movieListAdapter by inject<MovieListAdapter> {
        parametersOf({ id: Int -> navigateToMovieDetails(id) })
    }
    private var checkedTagId = R.id.chipPopularTag

    override fun onBind() = FragmentMovieListBinding.inflate(layoutInflater)

    override fun FragmentMovieListBinding.onViewCreated() {
        setupMoviesRecyclerView()
        setupFilterSelectionListener()
        setupObservers()
    }

    private fun setCheckedId(savedInstanceState: Bundle?) {
        savedInstanceState?.let { checkedTagId = it.getInt(CHECKED_CHIP_ID_KEY) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        saveCheckedId(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        setCheckedId(savedInstanceState)
        super.onViewStateRestored(savedInstanceState)
    }

    private fun saveCheckedId(outState: Bundle) {
        outState.putInt(CHECKED_CHIP_ID_KEY, binding.chipFilterGroup.checkedChipId)
    }

    private fun setupFilterSelectionListener() {
        binding.chipFilterGroup.setOnCheckedChangeListener { group, checkedId ->
            val type = getFilterType(checkedId)
            viewModel.dispatchViewAction(
                MovieListViewAction.FetchMovieList(type)
            )
        }
    }

    private fun getFilterType(checkedId: Int) = when (checkedId) {
        R.id.chipPopularTag -> MovieCategoryType.POPULAR
        R.id.chipTopRatedTag -> MovieCategoryType.TOP_RATED
        R.id.chipReleasedThisYearTag -> MovieCategoryType.RELEASED_THIS_YEAR
        R.id.chipUpcomingTag -> MovieCategoryType.UPCOMING
        else -> MovieCategoryType.POPULAR
    }

    private fun setupMoviesRecyclerView() = with(binding.rvMovies) {
        val spanCount = getBestGridSpanCount(
            resources.getInteger(R.integer.default_poster_width).px
        )
        val layoutManager = GridLayoutManager(requireContext(), spanCount)

        setHasFixedSize(true)
        this.layoutManager = layoutManager
        this.adapter = movieListAdapter
    }

    private fun setupObservers() {
        observeState()
        observeMovies()
    }

    private fun observeState() {
        observe(viewModel.viewState.state) {
            when (this) {
                BaseViewState.State.SUCCESS -> showSuccess()
                BaseViewState.State.LOADING -> showLoading()
                BaseViewState.State.ERROR -> showError()
            }
        }
    }

    private fun observeMovies() {
        observe(viewModel.viewState.movies) {
            movieListAdapter.updateMovies(this)
        }
    }

    private fun navigateToMovieDetails(id: Int) {
        val destination = MovieNavigationDirections.actionToMovieDetailsFragment(id)
        findNavController().navigateWithDirections(destination)
    }

    private companion object {
        const val CHECKED_CHIP_ID_KEY = "CHECK_CHIP_ID_KEY"
    }
}