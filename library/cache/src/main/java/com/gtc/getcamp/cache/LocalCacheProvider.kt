package com.gtc.getcamp.cache

import androidx.datastore.preferences.core.Preferences

interface LocalCacheProvider {

    suspend fun <T> storeValue(key: Preferences.Key<T>, value: T)

    suspend fun <T> readValue(key: Preferences.Key<T>, responseFunc: T.() -> Unit)
}