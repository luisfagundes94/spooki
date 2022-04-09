package com.luisfagundes.movies.presentation.list

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.commons_ui.adapter.MovieListAdapter
import com.luisfagundes.extensions.getBestGridSpanCount
import com.luisfagundes.extensions.navigateWithDirections
import com.luisfagundes.extensions.observe
import com.luisfagundes.extensions.px
import com.luisfagundes.movies.MovieNavigationDirections
import com.luisfagundes.movies.R
import com.luisfagundes.movies.databinding.FragmentMovieListBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class MovieListFragment : BaseFragment<FragmentMovieListBinding>(
    successViewId = R.id.movie_list_success_container,
    loadingViewId = R.id.movie_list_loading_container,
    errorViewId = R.id.movie_list_error_container
) {

    private val viewModel: MovieListViewModel by viewModel()
    private val movieListAdapter by inject<MovieListAdapter> {
        parametersOf({ id: Int -> navigateToMovieDetails(id) })
    }

    override fun onBind() = FragmentMovieListBinding.inflate(layoutInflater)

    override fun FragmentMovieListBinding.onViewCreated() {
        setupMoviesRecyclerView()
        setupFilterSelectionListener()
        setupObservers()

        viewModel.dispatchViewAction(MovieListViewAction.FetchMovieList)
    }

    private fun setupFilterSelectionListener() {
        binding.chipFilterGroup.setOnCheckedChangeListener { _, checkedId ->
            viewModel.updateCheckedFilterTag(checkedId)
            viewModel.dispatchViewAction(MovieListViewAction.FetchMovieList)
        }
    }

    private fun setupMoviesRecyclerView() = with(binding.rvMovies) {
        val spanCount = getBestGridSpanCount(resources.getInteger(R.integer.default_poster_width).px)
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
                MovieListViewState.State.SUCCESS -> showSuccess()
                MovieListViewState.State.LOADING -> showLoading()
                MovieListViewState.State.ERROR -> showError()
                else -> showError()
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
}