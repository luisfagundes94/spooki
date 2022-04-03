package com.luisfagundes.base

@Suppress("UNCHECKED_CAST")
sealed class Response<T> {
    abstract fun getValue(): T?
    abstract fun getError(): Exception?

    fun isError() = getError() != null
    fun isSuccess() = getValue() != null

    data class Success<T>(private val value: T) : Response<T>() {
        override fun getValue() = value
        override fun getError() = null
    }

    data class Error<T>(private val exception: Exception) : Response<T>() {
        override fun getValue() = null
        override fun getError() = exception
    }

    fun fold(onSuccess: (T) -> Any, onError: (Exception) -> Any): Any =
        when (this) {
            is Success -> onSuccess(getValue())
            is Error -> onError(getError())
        }

    companion object {
        suspend fun <V : Any> of(suspendFunction: suspend () -> V): Response<V> = try {
            val value = suspendFunction()
            Success(value)
        } catch (exception: Exception) {
            Error(exception)
        }

        suspend fun <V> listOf(suspendFunction: suspend () -> List<V>): Response<List<V>> = try {
            val value = suspendFunction()
            Success(value)
        } catch (exception: Exception) {
            Error(exception)
        }
    }
}
