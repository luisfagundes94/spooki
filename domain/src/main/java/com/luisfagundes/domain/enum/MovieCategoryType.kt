package com.luisfagundes.domain.enum

import com.luisfagundes.domain.R


enum class MovieCategoryType {
    POPULAR,
    TOP_RATED,
    RELEASED_THIS_YEAR,
    UPCOMING,
}

val MovieCategoryType.titleId: Int
    get() = when (this) {
        MovieCategoryType.POPULAR -> R.string.most_popular
        MovieCategoryType.RELEASED_THIS_YEAR -> R.string.released_this_year
        MovieCategoryType.TOP_RATED -> R.string.top_rated
        MovieCategoryType.UPCOMING -> R.string.upcoming
    }