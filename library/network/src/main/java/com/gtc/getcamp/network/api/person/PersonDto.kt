package com.gtc.getcamp.network.api.person

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonDto(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "name")
    val name: String?,
    @field:Json(name = "image_url")
    val imageUrl: String?,
    @field:Json(name = "about")
    val about: String?,
    @field:Json(name = "links")
    val links: List<String>?,
)