package com.gtc.getcamp.settings.domain.usecase

import com.gtc.getcamp.settings.domain.model.ThemeConfig
import com.gtc.getcamp.settings.domain.repository.UserPrefRepository
import javax.inject.Inject

class UpdateUserPrefUseCase @Inject constructor(
    private val userPrefRepository: UserPrefRepository,
) {
    suspend fun update(themeConfig: ThemeConfig) {
        return userPrefRepository.setThemeConfig(themeConfig)
    }
}
