package com.gtc.samples.getcamp.feature.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtc.samples.getcamp.feature.settings.domain.model.ThemeConfig
import com.gtc.samples.getcamp.feature.settings.domain.usecase.GetUserPrefUseCase
import com.gtc.samples.getcamp.feature.settings.domain.usecase.UpdateUserPrefUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    getUserPrefUseCase: GetUserPrefUseCase,
    private val updateUserPrefUseCase: UpdateUserPrefUseCase,
) : ViewModel() {

    val settingsUiState: StateFlow<SettingsUiState> =
        getUserPrefUseCase.get()
            .map { userPref ->
                SettingsUiState.Success(
                    settings = UserSettings(
                        themeConfig = ThemeConfig.find(userPref.intValue),
                    ),
                )
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue = SettingsUiState.Loading,
            )

    fun updateThemeConfig(themeConfig: ThemeConfig) {
        viewModelScope.launch {
            updateUserPrefUseCase.update(themeConfig)
        }
    }

    data class UserSettings(
        val themeConfig: ThemeConfig,
    )

    sealed interface SettingsUiState {
        object Loading : SettingsUiState
        data class Success(val settings: UserSettings) : SettingsUiState
    }
}