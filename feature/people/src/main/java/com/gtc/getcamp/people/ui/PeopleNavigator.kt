package com.gtc.getcamp.people.ui

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gtc.getcamp.navigator.NavigatorGraphApi
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