package com.luisfelipe.movies.domain.factory

import com.luisfelipe.movies.domain.enums.MovieCategoryEnum
import com.luisfelipe.movies.domain.enums.titleId
import com.luisfelipe.movies.domain.model.Media
import com.luisfelipe.movies.domain.model.MovieCategory
import com.luisfelipe.utils.StringProvider

interface MovieCategoryFactory {
    fun create(type: MovieCategoryEnum, mediaList: List<Media>): MovieCategory
}

class MovieCategoryFactoryImpl(
    private val stringProvider: StringProvider
) : MovieCategoryFactory {
    override fun create(type: MovieCategoryEnum, mediaList: List<Media>) = MovieCategory(
        title = stringProvider.getString(type.titleId),
        mediaList = mediaList
    )
}