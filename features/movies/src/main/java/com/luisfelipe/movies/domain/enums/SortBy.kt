package com.luisfelipe.movies.domain.enums

enum class SortBy(val value: String) {
    POPULARITY_DESC("popularity.desc"),
    PRIMARY_RELEASE_DATE_DESC("primary_release_date.desc"),
    RELEASE_DATE_DESC("release_date.desc"),
}