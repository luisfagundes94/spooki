package com.luisfagundes.home.presentation

import androidx.fragment.app.commit
import com.luisfagundes.base.BaseFragment
import com.luisfagundes.home.R
import com.luisfagundes.home.databinding.FragmentHomeBinding
import com.luisfagundes.movies.presentation.categories.MovieCategoryFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    successViewId = R.id.home_success_container,
    loadingViewId = R.id.home_loading_container,
    errorViewId = R.id.home_error_container
) {

    override fun onBind() = FragmentHomeBinding.inflate(layoutInflater)

    override fun FragmentHomeBinding.onViewCreated() {
        setupViews()
    }

    private fun setupViews() {
        setupMoviesContainer()
    }

    private fun setupMoviesContainer() = with(binding) {
        val movieCategoryFragment = MovieCategoryFragment.getInstance()

        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.movie_category_fragment_container, movieCategoryFragment)
        }
    }
}