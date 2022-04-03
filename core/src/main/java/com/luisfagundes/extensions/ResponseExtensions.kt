package com.luisfagundes.extensions

import com.luisfagundes.base.Response

fun <T> Response<T>.getResponse(): Response<T> =
    getValue()?.let { Response.Success(it) }
        ?: Response.Error(getError() ?: Exception())

fun <T> Response<T>.getResponse(onSuccess: (Any) -> T): Response<T> =
    getValue()?.let { Response.Success(onSuccess.invoke(it)) }
        ?: Response.Error(getError() ?: Exception())