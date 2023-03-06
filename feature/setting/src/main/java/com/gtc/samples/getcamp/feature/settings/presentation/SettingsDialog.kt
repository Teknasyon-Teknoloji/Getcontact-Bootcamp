package com.gtc.samples.getcamp.feature.settings.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gtc.samples.getcamp.feature.settings.domain.model.ThemeConfig
import kotlinx.coroutines.launch

@Composable
fun SettingsBottomSheetDialog(
    onDismiss: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsContent(
        onDismiss = onDismiss,
        settingsUiState = settingsUiState,
        onChangeThemeConfig = viewModel::updateThemeConfig,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsContent(
    onDismiss: () -> Unit,
    settingsUiState: SettingsViewModel.SettingsUiState,
    onChangeThemeConfig: (themeConfig: ThemeConfig) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true
    )

    BackHandler(modalSheetState.isVisible) {
        coroutineScope.launch { modalSheetState.hide() }
    }

    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
        sheetContent = {

        }
    ) {

    }
}
