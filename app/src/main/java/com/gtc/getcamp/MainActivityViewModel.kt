package com.gtc.getcamp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtc.samples.getcamp.feature.settings.domain.model.ThemeConfig
import com.gtc.samples.getcamp.feature.settings.domain.usecase.GetUserPrefUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    getUserPrefUseCase: GetUserPrefUseCase,
) : ViewModel() {

    val uiState: StateFlow<MainActivityUiState> = getUserPrefUseCase.get().map {
        MainActivityUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val themeConfig: ThemeConfig) : MainActivityUiState
}
