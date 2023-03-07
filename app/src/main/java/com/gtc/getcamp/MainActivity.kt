package com.gtc.getcamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
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
                startDestination = "/people"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppGraph(
    navigator: Navigator,
    vararg childGraphs: NavigatorGraphApi,
    startDestination: String,
) {
    val navController = rememberNavController()
    navigator.setNavHostController(navController)
    GetcampTheme {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primary,
                ) {
                    bottomNavItems.forEach { item ->
                        val selected = item.route == navigator.getCurrentRoute()
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navigator.popUp(navigator.getCurrentRoute())
                                navigator.navigateTo(item.route)
                            },
                            label = {
                                Text(
                                    text = item.name,
                                    fontWeight = FontWeight.SemiBold,
                                )
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = "${item.name} Icon",
                                )
                            }
                        )
                    }
                }
            },
        ) {
            Box(modifier = Modifier.padding(it)) {
                NavHost(navController = navController, startDestination = startDestination) {
                    registerGraph(*childGraphs)
                }
            }
        }
    }
}

val bottomNavItems = listOf(
    BottomNavItem(
        name = "People",
        route = "/people",
        icon = Icons.Rounded.Person,
    ),
    BottomNavItem(
        name = "Android",
        route = "/schedule/android",
        icon = Icons.Rounded.AddCircle,
    ),
    BottomNavItem(
        name = "iOS",
        route = "/schedule/ios",
        icon = Icons.Rounded.Star,
    ),
)

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
)
