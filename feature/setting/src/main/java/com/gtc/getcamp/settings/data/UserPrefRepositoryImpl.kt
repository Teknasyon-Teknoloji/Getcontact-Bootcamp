package com.gtc.getcamp.settings.data

import com.gtc.getcamp.cache.LocalCacheProvider
import com.gtc.getcamp.settings.domain.model.ThemeConfig
import com.gtc.getcamp.settings.domain.repository.UserPrefRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val PREF_KEY_THEME_CONFIG = "PREF_KEY_THEME_CONFIG"

class UserPrefRepositoryImpl @Inject constructor(
    private val localCacheProvider: LocalCacheProvider
) : UserPrefRepository {

    override suspend fun setThemeConfig(theme: ThemeConfig) {
        localCacheProvider.writeValue(PREF_KEY_THEME_CONFIG, theme.intValue)
    }

    override fun getThemeConfig(): Flow<ThemeConfig> {
        return localCacheProvider.readIntValue(PREF_KEY_THEME_CONFIG)
            .map { intValue ->
                return@map ThemeConfig.find(intValue)
            }
    }
}