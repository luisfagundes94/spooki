package com.luisfelipe.domain.factory

import com.luisfelipe.domain.enum.MediaCategoryType
import com.luisfelipe.domain.enum.titleId
import com.luisfelipe.domain.model.Media
import com.luisfelipe.domain.model.MediaCategory
import com.luisfelipe.utils.StringProvider

interface MediaCategoryFactory {
    fun create(type: MediaCategoryType, mediaList: List<Media>): MediaCategory
}

class MediaCategoryFactoryImpl(
    private val stringProvider: StringProvider
) : MediaCategoryFactory {
    override fun create(type: MediaCategoryType, mediaList: List<Media>) = MediaCategory(
        title = stringProvider.getString(type.titleId),
        mediaList = mediaList
    )
}