package com.gtc.getcamp.people.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun NetworkImage(
    url: String,
    networkImageType: NetworkImageType,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .size(Size.ORIGINAL)
            .build(),
    )

    when (networkImageType) {
        is NetworkImageType.ResizeNetworkImageType -> {
            NormalNetworkImage(
                painter = painter,
                networkImageType = networkImageType
            )
        }
        is NetworkImageType.CircleNetworkImageType -> {
            CircleNetworkImage(
                painter = painter,
                networkImageType = networkImageType
            )
        }
    }

}

@Composable
private fun CircleNetworkImage(
    painter: AsyncImagePainter,
    networkImageType: NetworkImageType.CircleNetworkImageType,
) {
    Box(
        modifier = Modifier
            .then(networkImageType.modifier)
            .clip(RoundedCornerShape(40.dp))
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentScale = ContentScale.Crop,
            contentDescription = "",
        )
    }
}

@Composable
private fun NormalNetworkImage(
    painter: AsyncImagePainter,
    networkImageType: NetworkImageType.ResizeNetworkImageType,
) {
    Image(
        modifier = Modifier.then(networkImageType.modifier),
        painter = painter,
        contentScale = ContentScale.Crop,
        contentDescription = "",
    )
}