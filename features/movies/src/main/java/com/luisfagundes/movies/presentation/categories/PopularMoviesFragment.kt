package com.luisfagundes.movies.presentation.categories

import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.movies.presentation.baseCategory.BaseMovieCategoryFragment

class PopularMoviesFragment : BaseMovieCategoryFragment(MovieCategoryType.POPULAR) {

    companion object {
        fun createInstance() = PopularMoviesFragment()
    }
}