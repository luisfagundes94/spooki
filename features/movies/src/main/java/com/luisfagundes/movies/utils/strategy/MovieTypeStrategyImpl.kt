package com.luisfagundes.movies.utils.strategy

import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.movies.R

interface MovieTypeStrategy {
    fun getFilterType(checkedId: Int): MovieCategoryType
}

object MovieTypeStrategyImpl: MovieTypeStrategy {
    override fun getFilterType(checkedId: Int) = when (checkedId) {
        R.id.chipPopularTag -> MovieCategoryType.POPULAR
        R.id.chipTopRatedTag -> MovieCategoryType.TOP_RATED
        R.id.chipReleasedThisYearTag -> MovieCategoryType.RELEASED_THIS_YEAR
        R.id.chipUpcomingTag -> MovieCategoryType.UPCOMING
        else -> MovieCategoryType.POPULAR
    }
}