package com.gtc.getcamp.network.api.schedule

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class ScheduleDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "date")
    val date: Date?,
    @field:Json(name = "hours")
    val hours: String?,
    @field:Json(name = "platform")
    val platform: String?,
    @field:Json(name = "speaker")
    val speakerId: Int?,
    @field:Json(name = "topics")
    val topics: List<String>?,
    @field:Json(name = "image_url")
    val imageUrl: String?,
)