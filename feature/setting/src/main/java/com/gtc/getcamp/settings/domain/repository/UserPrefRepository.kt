package com.gtc.getcamp.settings.domain.repository

import com.gtc.getcamp.settings.domain.model.ThemeConfig
import kotlinx.coroutines.flow.Flow

interface UserPrefRepository {

    suspend fun setThemeConfig(theme: ThemeConfig)

    fun getThemeConfig(): Flow<ThemeConfig>

}