package com.gtc.getcamp.people.ui.navigator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gtc.getcamp.navigator.NavigatorGraphApi
import com.gtc.getcamp.people.ui.PeopleScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleNavigator @Inject constructor(): NavigatorGraphApi {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable("/persons"){
            PeopleScreen()
        }
    }

}