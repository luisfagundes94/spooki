package com.luisfelipe.extensions

import com.luisfelipe.base.Response

fun <T> List<Response<List<T>>>.mergeResponses(): Response<List<T>> {
    val responseList = mutableListOf<T>()

    this.forEach { response ->
        if (response.isAnError()) return Response.Error(response.getError() ?: Exception())

        response.getValue()?.let {
            responseList.addAll(it)
        }
    }

    return Response.Success(responseList)
}