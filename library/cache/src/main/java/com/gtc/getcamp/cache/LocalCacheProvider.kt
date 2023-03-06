package com.gtc.getcamp.cache

import kotlinx.coroutines.flow.Flow

interface LocalCacheProvider {

    suspend fun <T : Any> writeValue(key: String, value: T)
    fun readIntValue(key: String): Flow<Int>
}