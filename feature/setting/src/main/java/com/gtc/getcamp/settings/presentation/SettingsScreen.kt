package com.gtc.getcamp.settings.presentation

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gtc.getcamp.settings.domain.model.ThemeConfig
import kotlinx.coroutines.launch


@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val settingsUiState by viewModel.settingsUiState.collectAsStateWithLifecycle()
    SettingsContent(
        settingsUiState = settingsUiState,
        onChangeThemeConfig = viewModel::updateThemeConfig,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsContent(
    settingsUiState: SettingsUiState,
    onChangeThemeConfig: (themeConfig: ThemeConfig) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Expanded,
        confirmStateChange = { it != BottomSheetValue.Expanded },
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = bottomSheetState
    )

    BackHandler(bottomSheetState.isExpanded) {
        coroutineScope.launch { bottomSheetState.collapse() }
    }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 30.dp)
            ) {
                Text(
                    text = "Settings",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    ),
                )
                Divider(Modifier.padding(vertical = 16.dp))
                when (settingsUiState) {
                    is SettingsUiState.Success -> {
                        SettingsPanel(
                            settings = settingsUiState.userSettings,
                            onChangeThemeConfig = onChangeThemeConfig
                        )
                    }
                    SettingsUiState.Loading -> {
                        // TODO show progress
                    }
                }
                AboutContent()
            }
        },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp),
    ){

    }
}


@Composable
private fun SettingsPanel(
    settings: UserSettings,
    onChangeThemeConfig: (themeConfig: ThemeConfig) -> Unit,
) {

    SettingsSectionTitle(text = "Theme preference")
    Column(Modifier.selectableGroup()) {
        SettingsDialogThemeChooserRow(
            text = "System default",
            selected = settings.themeConfig == ThemeConfig.FOLLOW_SYSTEM,
            onClick = { onChangeThemeConfig(ThemeConfig.FOLLOW_SYSTEM) },
        )
        SettingsDialogThemeChooserRow(
            text = "Light",
            selected = settings.themeConfig == ThemeConfig.LIGHT,
            onClick = { onChangeThemeConfig(ThemeConfig.LIGHT) },
        )
        SettingsDialogThemeChooserRow(
            text = "Dark",
            selected = settings.themeConfig == ThemeConfig.DARK,
            onClick = { onChangeThemeConfig(ThemeConfig.DARK) },
        )
    }

}

@Composable
private fun AboutContent() {
    Column(modifier = Modifier.padding(top = 16.dp)) {
        Text(
            text = "About",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            ),
        )
        Divider(Modifier.padding(vertical = 16.dp))
        TextLink(
            text = "Web Page",
            url = WEB_PAGE_LINK
        )

        Text(
            modifier = Modifier
                .padding(vertical = 16.dp),
            text = "Version: 1.0.0",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun SettingsSectionTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
    )
}

@Composable
private fun TextLink(text: String, url: String) {
    val launchResourceIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    val context = LocalContext.current

    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .clickable {
                ContextCompat.startActivity(context, launchResourceIntent, null)
            },
    )
}

@Composable
fun SettingsDialogThemeChooserRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}


@Preview
@Composable
private fun PreviewSettingsDialog() {
    MaterialTheme {
        SettingsContent(
            settingsUiState = SettingsUiState.Success(
                UserSettings(
                    themeConfig = ThemeConfig.FOLLOW_SYSTEM,
                ),
            ),
            onChangeThemeConfig = {},
        )
    }
}

@Preview
@Composable
private fun PreviewSettingsDialogLoading() {
    MaterialTheme {
        SettingsContent(
            settingsUiState = SettingsUiState.Loading,
            onChangeThemeConfig = {},
        )
    }
}

private const val WEB_PAGE_LINK = "https://policies.google.com/privacy"