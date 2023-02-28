package com.gtc.getcamp.navigator

import androidx.navigation.NavGraphBuilder

interface NavigatorGraphApi {

    fun registerGraph(navGraphBuilder: NavGraphBuilder)
}

fun NavGraphBuilder.registerGraph(navigatorApi: NavigatorGraphApi) {
    navigatorApi.registerGraph(navGraphBuilder = this)
}

fun NavGraphBuilder.registerGraph(vararg navigatorApis: NavigatorGraphApi) {
    navigatorApis.forEach { navigatorApi ->
        navigatorApi.registerGraph(navGraphBuilder = this)
    }
}