package com.luisfelipe.home.presentation

import androidx.fragment.app.commit
import com.luisfelipe.base.BaseFragment
import com.luisfelipe.home.R
import com.luisfelipe.home.databinding.FragmentHomeBinding
import com.luisfelipe.movies.presentation.categories.MovieCategoryFragment

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