package com.gtc.samples.getcamp.feature.settings.domain.usecase

import com.gtc.samples.getcamp.feature.settings.domain.model.ThemeConfig
import com.gtc.samples.getcamp.feature.settings.domain.repository.UserPrefRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserPrefUseCase @Inject constructor(
    private val userPrefRepository: UserPrefRepository,
) {
    fun get(): Flow<ThemeConfig> {
        return userPrefRepository.getThemeConfig()
    }
}

