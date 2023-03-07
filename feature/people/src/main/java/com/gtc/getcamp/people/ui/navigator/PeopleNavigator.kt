package com.gtc.getcamp.people.ui.navigator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.gtc.getcamp.navigator.NavigatorGraphApi
import com.gtc.getcamp.people.ui.people.PeopleScreen
import com.gtc.getcamp.people.ui.persondetail.PersonDetailScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleNavigator @Inject constructor(): NavigatorGraphApi {

    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable("/people"){
            PeopleScreen()
        }
        navGraphBuilder.composable("/person/{personId}",
            arguments = listOf(navArgument("personId") { type = NavType.StringType })
        ){
            PersonDetailScreen()
        }
    }

}