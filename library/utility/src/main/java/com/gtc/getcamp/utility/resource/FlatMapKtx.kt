@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package com.gtc.getcamp.utility.resource

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow

inline fun <T, R> FlowResource<T>.flatMapLatestOnSuccess(
    crossinline transform: suspend (value: T) -> FlowResource<R>
): FlowResource<R> {
    return flatMapLatest {
        return@flatMapLatest when (it) {
            is Resource.Success -> transform.invoke(it.data)
            is Resource.Error -> flow { emit(it) }
        }
    }
}

inline fun <T, R> FlowResource<T>.flatMapConcatOnSuccess(
    crossinline transform: suspend (value: T) -> FlowResource<R>
): FlowResource<R> {
    return flatMapConcat {
        return@flatMapConcat when (it) {
            is Resource.Success -> transform.invoke(it.data)
            is Resource.Error -> flow { emit(it) }
        }
    }
}

inline fun <T, R> FlowResource<T>.flatMapMergeOnSuccess(
    crossinline transform: suspend (value: T) -> FlowResource<R>
): FlowResource<R> {
    return flatMapMerge {
        return@flatMapMerge when (it) {
            is Resource.Success -> transform.invoke(it.data)
            is Resource.Error -> flow { emit(it) }
        }
    }
}

