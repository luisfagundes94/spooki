package com.luisfelipe.movies.domain.enums

import com.luisfelipe.movies.R

enum class MovieCategoryEnum {
    POPULAR,
    PRIMARY_RELEASED,
    TRENDING,
    NOW_PLAYING,
    OSCAR_NOMINEES
}

val MovieCategoryEnum.titleId: Int
    get() = when (this) {
        MovieCategoryEnum.POPULAR -> R.string.most_popular
        MovieCategoryEnum.NOW_PLAYING -> R.string.now_playing
        MovieCategoryEnum.OSCAR_NOMINEES -> R.string.oscar_nominees
        MovieCategoryEnum.PRIMARY_RELEASED -> R.string.primary_released_date_desc
        MovieCategoryEnum.TRENDING -> R.string.trending
    }
