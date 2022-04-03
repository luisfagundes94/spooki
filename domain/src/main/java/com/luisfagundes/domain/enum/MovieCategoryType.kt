package com.luisfagundes.domain.enum

import com.luisfagundes.domain.R


enum class MovieCategoryType {
    POPULAR,
    PRIMARY_RELEASED,
    TOP_RATED,
    UPCOMING,
    TRENDING,
    NOW_PLAYING,
    OSCAR_NOMINEES,
    RELEASED_THIS_YEAR
}

val MovieCategoryType.titleId: Int
    get() = when (this) {
        MovieCategoryType.POPULAR -> R.string.most_popular
        MovieCategoryType.NOW_PLAYING -> R.string.now_playing
        MovieCategoryType.OSCAR_NOMINEES -> R.string.oscar_nominees
        MovieCategoryType.PRIMARY_RELEASED -> R.string.primary_released_date_desc
        MovieCategoryType.TRENDING -> R.string.trending
        MovieCategoryType.RELEASED_THIS_YEAR -> R.string.released_this_year
        MovieCategoryType.TOP_RATED -> R.string.top_rated
        MovieCategoryType.UPCOMING -> R.string.upcoming
    }
