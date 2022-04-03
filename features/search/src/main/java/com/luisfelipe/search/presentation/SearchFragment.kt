package com.luisfagundes.search.presentation

import android.view.Menu
import android.view.MenuInflater
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.base.BaseViewState
import com.luisfagundes.extensions.hideVisibility
import com.luisfagundes.extensions.observe
import com.luisfagundes.search.R
import com.luisfagundes.search.databinding.FragmentSearchBinding
import com.luisfagundes.utils.GridSpacingItemDecoration
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    successViewId = R.id.search_success_container,
    loadingViewId = R.id.search_loading_container,
    errorViewId = R.id.search_error_container,
) {

    private val viewModel: SearchViewModel by viewModel()
    private val searchMovieAdapter: SearchMovieAdapter by inject {
        parametersOf({ id: Int -> navigateToMovieDetails(id) })
    }

    override fun onBind() = FragmentSearchBinding.inflate(layoutInflater)

    override fun FragmentSearchBinding.onViewCreated() {
        setupViews()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView?

        searchView?.setupSearchView()

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun SearchView.setupSearchView() {
        queryHint = getString(R.string.hint_search_movies)

        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.dispatchViewAction(
                        SearchViewAction.SearchMoviesAndTvShows(query = it)
                    )
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                query?.let {
                    viewModel.dispatchViewAction(
                        SearchViewAction.SearchMoviesAndTvShows(query = it)
                    )
                }
                return false
            }
        })
    }

    private fun setupViews() {
        setHasOptionsMenu(true)
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding.rvMoviesAndTvShows) {
        val layoutManager = GridLayoutManager(
            context,
            DEFAULT_ITEM_SPAN_COUNT,
            GridLayoutManager.VERTICAL,
            false
        )

        val itemDecoration = GridSpacingItemDecoration(
            DEFAULT_ITEM_SPAN_COUNT,
            0,
            false
        )

        addItemDecoration(itemDecoration)
        setHasFixedSize(true)
        this.layoutManager = layoutManager
        this.adapter = searchMovieAdapter
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

        observe(viewModel.viewState.movies) {
            searchMovieAdapter.updateList(this)
            announceItemsFoundForAccessibility()
        }
    }

    private fun announceItemsFoundForAccessibility() {
        val message =  requireContext().resources.getQuantityString(
            R.plurals.items_found,
            searchMovieAdapter.itemCount,
            searchMovieAdapter.itemCount,
        )
        view?.announceForAccessibility(message)
    }

    private fun navigateToMovieDetails(id: Int) {
        val destination = SearchFragmentDirections
            .actionSearchFragmentToMovieDetailsFragment(id)
        findNavController().navigate(destination)
    }

    override fun showError() = with(binding.searchErrorContainer) {
        super.showError()
    }

    private companion object {
        const val DEFAULT_ITEM_SPAN_COUNT = 3
    }
}