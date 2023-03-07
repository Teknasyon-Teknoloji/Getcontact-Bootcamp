package com.gtc.getcamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gtc.getcamp.navigator.Navigator
import com.gtc.getcamp.navigator.NavigatorGraphApi
import com.gtc.getcamp.navigator.registerGraph
import com.gtc.getcamp.people.ui.navigator.PeopleNavigator
import com.gtc.getcamp.schedule.presentation.ScheduleNavigator
import com.gtc.getcamp.ui.theme.GetcampTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var peopleNavigator: PeopleNavigator

    @Inject
    lateinit var scheduleNavigator: ScheduleNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGraph(
                navigator = navigator,
                childGraphs = arrayOf(peopleNavigator, scheduleNavigator),
                startDestination = "/schedule"
            )
            GetcampTheme {

            }
        }
    }
}

@Composable
fun AppGraph(
    navigator: Navigator,
    vararg childGraphs: NavigatorGraphApi,
    startDestination: String,
) {
    val navController = rememberNavController()
    navigator.setNavHostController(navController)

    NavHost(navController = navController, startDestination = startDestination) {
        registerGraph(*childGraphs)
    }
}