package com.luisfagundes.movies.presentation.categories

import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.movies.presentation.baseCategory.BaseMovieCategoryFragment

class TopRatedMoviesFragment : BaseMovieCategoryFragment(MovieCategoryType.TOP_RATED) {
    companion object {
        fun createInstance() = TopRatedMoviesFragment()
    }
}