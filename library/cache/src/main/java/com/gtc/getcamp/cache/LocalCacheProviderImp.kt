package com.gtc.getcamp.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class LocalCacheProviderImp @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalCacheProvider {

    override suspend fun <T> storeValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { pref ->
            pref[key] = value
        }
    }

    override suspend fun <T> readValue(key: Preferences.Key<T>, responseFunc: T.() -> Unit) {
        dataStore.getFromLocalStorage(key) {
            responseFunc.invoke(this)
        }
    }

    private suspend fun <T> DataStore<Preferences>.getFromLocalStorage(
        PreferencesKey: Preferences.Key<T>,
        func: T.() -> Unit
    ) {
        data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { pref ->
            pref[PreferencesKey]
        }.collect {
            it?.let { func.invoke(it as T) }
        }
    }
}