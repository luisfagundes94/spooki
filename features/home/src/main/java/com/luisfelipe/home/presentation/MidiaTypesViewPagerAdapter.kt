package com.luisfelipe.home.presentation

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.luisfelipe.movies.presentation.categories.MovieCategoryFragment
import com.luisfelipe.shows.presentation.categories.ShowCategoriesFragment


class MidiaTypesViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private companion object {
        const val NUMBER_OF_TABS = 2
    }

    override fun getItemCount() = NUMBER_OF_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieCategoryFragment()
            1 -> ShowCategoriesFragment()
            else -> MovieCategoryFragment()
        }
    }
}