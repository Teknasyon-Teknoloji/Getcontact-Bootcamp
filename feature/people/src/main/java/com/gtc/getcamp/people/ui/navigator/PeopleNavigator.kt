package com.gtc.getcamp.people.ui.navigator

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.gtc.getcamp.navigator.NavigatorGraphApi
import com.gtc.getcamp.people.ui.people.PeopleScreen
import com.gtc.getcamp.people.ui.persondetail.PersonDetailScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleNavigator @Inject constructor(): NavigatorGraphApi {

    @OptIn(ExperimentalMaterialNavigationApi::class)
    override fun registerGraph(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable("/people"){
            PeopleScreen()
        }
        navGraphBuilder.bottomSheet("/person/{personId}",
            arguments = listOf(navArgument("personId") { type = NavType.IntType })
        ){
            PersonDetailScreen()
        }
    }

}