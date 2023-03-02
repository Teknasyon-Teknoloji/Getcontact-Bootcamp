package com.gtc.getcamp.cache

import androidx.datastore.preferences.core.Preferences

interface LocalCacheProvider {

    suspend fun <T : Any> writeValue(key: String, value: T)
    suspend fun <T> readValue(key: Preferences.Key<T>, responseFunc: T.() -> Unit)
}