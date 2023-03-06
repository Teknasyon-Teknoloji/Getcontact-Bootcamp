package com.gtc.samples.getcamp.feature.settings.domain.usecase

import com.gtc.samples.getcamp.feature.settings.domain.model.ThemeConfig
import com.gtc.samples.getcamp.feature.settings.domain.repository.UserPrefRepository
import javax.inject.Inject

class UpdateUserPrefUseCase @Inject constructor(
    private val userPrefRepository: UserPrefRepository,
) {
    suspend fun update(themeConfig: ThemeConfig) {
        return userPrefRepository.setThemeConfig(themeConfig)
    }
}
