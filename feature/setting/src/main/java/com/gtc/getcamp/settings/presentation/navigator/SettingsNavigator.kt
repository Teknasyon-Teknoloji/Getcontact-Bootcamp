package com.gtc.getcamp.settings.presentation.navigator

import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.gtc.getcamp.navigator.NavigatorGraphApi
import com.gtc.getcamp.settings.presentation.SettingsScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsNavigator @Inject constructor() : NavigatorGraphApi {

    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.bottomSheet("/settings") {
            SettingsScreen()
        }
    }
}