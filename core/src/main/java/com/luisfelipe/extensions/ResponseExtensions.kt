package com.luisfelipe.extensions

import com.luisfelipe.base.Response
import java.lang.Exception

fun <T> List<Response<List<T>>>.mergeResponses(): Response<List<T>> {
    val responseList = mutableListOf<T>()

    this.forEach { response ->
        if (response.isAnError()) return Response.Error(response.error() ?: Exception())

        response.value()?.let {
            responseList.addAll(it)
        }
    }

    return Response.Success(responseList)
}