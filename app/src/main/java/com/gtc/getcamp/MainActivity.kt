package com.gtc.getcamp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gtc.getcamp.navigator.Navigator
import com.gtc.getcamp.navigator.NavigatorGraphApi
import com.gtc.getcamp.navigator.registerGraph
import com.gtc.getcamp.people.ui.navigator.PeopleNavigator
import com.gtc.getcamp.settings.domain.model.ThemeConfig
import com.gtc.getcamp.settings.ui.navigator.SettingsNavigator
import com.gtc.getcamp.schedule.presentation.ScheduleNavigator
import com.gtc.getcamp.ui.theme.GetcampTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var peopleNavigator: PeopleNavigator

    @Inject
    lateinit var scheduleNavigator: ScheduleNavigator

    @Inject
    lateinit var settingsNavigator: SettingsNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var uiState: MainActivityUiState by mutableStateOf(MainActivityUiState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState
                    .onEach {
                        uiState = it
                    }.collect()
            }
        }

        setContent {
            val darkTheme = shouldShowInDarkTheme(uiState)

            AppGraph(
                navigator = navigator,
                childGraphs = arrayOf(peopleNavigator, scheduleNavigator, settingsNavigator),
                startDestination = "/people"
            )
            GetcampTheme(
                darkTheme = darkTheme
            ) {

            }
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
    var selectedItem by remember {
        mutableStateOf(bottomNavItems[0])
    }
    val navController = rememberNavController()
    navigator.setNavHostController(navController)
    GetcampTheme {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary,
                ) {
                    bottomNavItems.forEach { item ->
                        val selected = item == selectedItem
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                selectedItem = item
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
                                    painter = painterResource(id = item.icon),
                                    contentDescription = "${item.name} Icon",
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                                selectedTextColor = MaterialTheme.colorScheme.onPrimary,
                                indicatorColor = MaterialTheme.colorScheme.primary,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                            ),
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
        icon = R.drawable.ic_people,
    ),
    BottomNavItem(
        name = "Android",
        route = "/schedule/android",
        icon = R.drawable.ic_android,
    ),
    BottomNavItem(
        name = "iOS",
        route = "/schedule/ios",
        icon = R.drawable.ic_ios,
    ),
)

data class BottomNavItem(
    val name: String,
    val route: String,
    @DrawableRes val icon: Int,
)


@Composable
private fun shouldShowInDarkTheme(
    uiState: MainActivityUiState,
): Boolean {
    return when (uiState) {
        is MainActivityUiState.Loading -> isSystemInDarkTheme()
        is MainActivityUiState.Success -> when (uiState.themeConfig) {
            ThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
            ThemeConfig.DARK -> false
            ThemeConfig.LIGHT -> true
        }
    }
}