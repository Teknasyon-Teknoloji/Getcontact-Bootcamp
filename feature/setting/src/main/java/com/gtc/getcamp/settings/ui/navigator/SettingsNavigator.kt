package com.gtc.getcamp.settings.ui.navigator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gtc.getcamp.navigator.NavigatorGraphApi
import com.gtc.getcamp.settings.ui.SettingsScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsNavigator @Inject constructor() : NavigatorGraphApi {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable("/settings") {
            SettingsScreen()
        }
    }
}