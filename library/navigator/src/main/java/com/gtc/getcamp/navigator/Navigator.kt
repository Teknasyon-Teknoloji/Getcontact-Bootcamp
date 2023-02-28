package com.gtc.getcamp.navigator

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.navigation.NavHostController
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor(@ApplicationContext val context: Context) {

    private var navHostController: NavHostController? = null

    fun setNavHostController(navHostController: NavHostController) {
        this.navHostController = navHostController
    }

    fun navigateTo(route: String) {
        navHostController?.navigate(route = route) {
            launchSingleTop = true
        }
    }

    fun popUp(route: String) {
        navHostController?.popBackStack(route, true)
    }

    fun popBackStack() {
        if (navHostController?.backQueue.isNullOrEmpty().not()) navHostController?.popBackStack()
    }

    fun navigateToExternalWeb(uri: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        context.startActivity(intent)
    }

    fun getCurrentRoute(): String {
        return navHostController?.currentDestination?.route.orEmpty()
    }
}