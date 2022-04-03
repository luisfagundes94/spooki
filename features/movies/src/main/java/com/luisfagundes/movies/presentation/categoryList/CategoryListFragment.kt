package com.luisfagundes.movies.presentation.categoryList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.luisfagundes.movies.R
import com.luisfagundes.movies.databinding.FragmentCategoryListBinding
import com.luisfagundes.movies.presentation.categories.PopularMoviesFragment
import com.luisfagundes.movies.presentation.categories.TopRatedMoviesFragment

class CategoryListFragment: Fragment(R.layout.fragment_category_list) {

    private lateinit var binding: FragmentCategoryListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupBinding(view)
        setupCategories()
    }

    private fun setupBinding(view: View) {
        binding = FragmentCategoryListBinding.bind(view)
    }

    private fun setupCategories() {
        val popularMoviesFragment = PopularMoviesFragment.createInstance()
        replaceContainerWithFragment(R.id.popularMoviesFragmentContainer, popularMoviesFragment)

        val topRatedMoviesFragment = TopRatedMoviesFragment.createInstance()
        replaceContainerWithFragment(R.id.topRatedMoviesFragmentContainer, topRatedMoviesFragment)
    }

    private fun replaceContainerWithFragment(containerId: Int, fragment: Fragment) {
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace(containerId, fragment)
        }
    }
}