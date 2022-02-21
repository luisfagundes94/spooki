package com.luisfelipe.domain.enum

import com.luisfelipe.domain.R


enum class MediaCategoryType {
    POPULAR,
    PRIMARY_RELEASED,
    TOP_RATED,
    UPCOMING,
    TRENDING,
    NOW_PLAYING,
    OSCAR_NOMINEES,
    RELEASED_THIS_YEAR
}

val MediaCategoryType.titleId: Int
    get() = when (this) {
        MediaCategoryType.POPULAR -> R.string.most_popular
        MediaCategoryType.NOW_PLAYING -> R.string.now_playing
        MediaCategoryType.OSCAR_NOMINEES -> R.string.oscar_nominees
        MediaCategoryType.PRIMARY_RELEASED -> R.string.primary_released_date_desc
        MediaCategoryType.TRENDING -> R.string.trending
        MediaCategoryType.RELEASED_THIS_YEAR -> R.string.released_this_year
        MediaCategoryType.TOP_RATED -> R.string.top_rated
        MediaCategoryType.UPCOMING -> R.string.upcoming
    }
