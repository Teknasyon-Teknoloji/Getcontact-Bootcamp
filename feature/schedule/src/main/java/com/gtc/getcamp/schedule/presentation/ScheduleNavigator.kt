package com.gtc.getcamp.schedule.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gtc.getcamp.navigator.NavigatorGraphApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleNavigator @Inject constructor() : NavigatorGraphApi {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(
            "/schedule/{platform}",
            arguments = listOf(
                navArgument("platform") {
                    type = NavType.StringType
                    defaultValue = "Android"
                })
        ) {
            ScheduleListScreen()
        }
        navGraphBuilder.composable(
            "/schedule",
            arguments = listOf(
                navArgument("platform") {
                    type = NavType.StringType
                    defaultValue = "Android"
                })
        ) {
            ScheduleListScreen()
        }
    }
}