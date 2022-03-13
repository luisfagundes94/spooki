package com.luisfagundes.domain.factory

import com.luisfagundes.domain.enum.MovieCategoryType
import com.luisfagundes.domain.enum.titleId
import com.luisfagundes.domain.model.Movie
import com.luisfagundes.domain.model.MovieCategory
import com.luisfagundes.utils.StringProvider

interface MovieCategoryFactory {
    fun create(type: MovieCategoryType, movieList: List<Movie>): MovieCategory
}

class MovieCategoryFactoryImpl(
    private val stringProvider: StringProvider
) : MovieCategoryFactory {
    override fun create(type: MovieCategoryType, movieList: List<Movie>) = MovieCategory(
        title = stringProvider.getString(type.titleId),
        movieList = movieList
    )
}