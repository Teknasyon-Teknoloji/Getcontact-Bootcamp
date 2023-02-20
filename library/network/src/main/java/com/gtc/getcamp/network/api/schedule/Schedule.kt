package com.gtc.getcamp.network.api.schedule

import com.gtc.getcamp.network.api.person.Person
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Schedule(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "date")
    val date: String?,
    @field:Json(name = "hours")
    val hours: String?,
    @field:Json(name = "speaker")
    val speaker: Person?,
    @field:Json(name = "topics")
    val topics: List<String>?,
)