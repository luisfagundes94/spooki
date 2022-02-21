package com.luisfelipe.domain.enum

enum class SortByType(val value: String) {
    POPULARITY_DESC("popularity.desc"),
    PRIMARY_RELEASE_DATE_DESC("primary_release_date.desc"),
    RELEASE_DATE_DESC("release_date.desc"),
}