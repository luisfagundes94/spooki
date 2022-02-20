package com.luisfelipe.base

@Suppress("UNCHECKED_CAST")
sealed class Response<T> {
    abstract fun value(): T?
    abstract fun error(): Exception?

    fun isAnError() = error() != null

    data class Success<T>(private val value: T) : Response<T>() {
        override fun value() = value
        override fun error() = null
    }

    data class Error<T>(private val exception: Exception) : Response<T>() {
        override fun value() = null
        override fun error() = exception
    }

    fun fold(onSuccess: (T) -> Any, onError: (Exception) -> Any): Any =
        when (this) {
            is Success -> onSuccess(value())
            is Error -> onError(error())
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
