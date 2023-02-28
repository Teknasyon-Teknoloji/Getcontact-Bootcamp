package com.gtc.getcamp.utility.resource

suspend fun <T> FlowResource<T>.collect(
    onSuccess: (suspend (T) -> Unit)? = null,
    onError: (suspend (message: String, code: Int) -> Unit)? = null,
) = collect {
    when (it) {
        is Resource.Success -> onSuccess?.invoke(it.data)
        is Resource.Error -> onError?.invoke(it.message, it.code)
    }
}

suspend inline fun <T> FlowResource<T>.collectSuccess(
    crossinline onSuccess: ((data: T) -> Unit)
) = collect {
    when (it) {
        is Resource.Success -> onSuccess(it.data)
        else -> {}
    }
}

suspend inline fun <T> FlowResource<T>.collectError(
    crossinline onError: (message: String, code: Int) -> Unit
) = collect {
    when (it) {
        is Resource.Error -> onError(it.message, it.code)
        else -> {}
    }
}
