package com.luisfelipe.domain.factory

import com.luisfelipe.domain.enum.MovieCategoryType
import com.luisfelipe.domain.enum.titleId
import com.luisfelipe.domain.model.Movie
import com.luisfelipe.domain.model.MovieCategory
import com.luisfelipe.utils.StringProvider

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