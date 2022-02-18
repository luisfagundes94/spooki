package com.luisfelipe.home.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.luisfelipe.movies.presentation.categories.MovieCategoriesFragment
import com.luisfelipe.shows.presentation.categories.ShowCategoriesFragment


class MidiaTypesViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private companion object {
        const val NUMBER_OF_TABS = 2
    }

    override fun getItemCount() = NUMBER_OF_TABS

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieCategoriesFragment()
            1 -> ShowCategoriesFragment()
            else -> MovieCategoriesFragment()
        }
    }
}