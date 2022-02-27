package com.luisfelipe.search.presentation

import android.widget.ImageView
import com.luisfelipe.base.BaseFragment
import com.luisfelipe.base.BaseViewState
import android.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.luisfelipe.extensions.empty
import com.luisfelipe.extensions.observe
import com.luisfelipe.search.R
import com.luisfelipe.search.databinding.FragmentSearchBinding
import com.luisfelipe.utils.GridSpacingItemDecoration
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

    private fun setupViews() {
        setupSearchView()
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

    private fun setupSearchView() = with(binding.svRecipes) {
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

        setupSearchViewCloseButtonListener()
    }

    private fun SearchView.setupSearchViewCloseButtonListener() {
        val searchViewCloseButtonId = requireContext().resources.getIdentifier(
            SEARCH_VIEW_CLOSE_BUTTON_DEFAULT_ID,
            null,
            null
        )
        val searchViewCloseButton = findViewById<ImageView>(searchViewCloseButtonId)

        searchViewCloseButton.setOnClickListener {
            setDefaultSearchViewState()
        }
    }

    private fun SearchView.setDefaultSearchViewState() {
        setQuery(String.empty(), false)
        clearFocus()
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
        const val SEARCH_VIEW_CLOSE_BUTTON_DEFAULT_ID = "android:id/search_close_btn"
        const val DEFAULT_ITEM_SPAN_COUNT = 3
    }
}