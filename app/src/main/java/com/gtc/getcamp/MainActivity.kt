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
import com.gtc.getcamp.ui.theme.GetcampTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGraph(
                navigator = navigator,
                childGraphs = emptyArray(),
                startDestination = "TODO please provide start destinationÏ"
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