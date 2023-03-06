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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.gtc.getcamp.navigator.Navigator
import com.gtc.getcamp.navigator.NavigatorGraphApi
import com.gtc.getcamp.navigator.registerGraph
import com.gtc.getcamp.people.ui.navigator.PeopleNavigator
import com.gtc.getcamp.ui.theme.GetcampTheme
import com.gtc.samples.getcamp.feature.settings.domain.model.ThemeConfig
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigator: Navigator

    val viewModel: MainActivityViewModel by viewModels()

    @Inject
    lateinit var peopleNavigator: PeopleNavigator

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
                childGraphs = arrayOf(peopleNavigator) ,
                startDestination = "/persons"
            )
            GetcampTheme(
                darkTheme = darkTheme
            ) {

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