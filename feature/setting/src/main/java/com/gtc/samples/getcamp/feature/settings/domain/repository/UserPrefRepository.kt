package com.gtc.samples.getcamp.feature.settings.domain.repository

import com.gtc.samples.getcamp.feature.settings.domain.model.ThemeConfig
import kotlinx.coroutines.flow.Flow

interface UserPrefRepository {

    suspend fun setThemeConfig(theme: ThemeConfig)

    fun getThemeConfig(): Flow<ThemeConfig>

}