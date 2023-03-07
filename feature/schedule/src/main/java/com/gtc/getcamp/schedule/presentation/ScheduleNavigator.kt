package com.gtc.getcamp.schedule.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gtc.getcamp.navigator.NavigatorGraphApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScheduleNavigator @Inject constructor() : NavigatorGraphApi {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable("/schedule") {
            ScheduleListScreen()
        }
    }
}