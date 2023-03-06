package com.gtc.getcamp.people.ui.component

import androidx.compose.ui.Modifier

sealed class NetworkImageType {
    data class CircleNetworkImageType(
        val modifier: Modifier
    ) : NetworkImageType()

    data class ResizeNetworkImageType(
        val modifier: Modifier
    ) : NetworkImageType()
}
