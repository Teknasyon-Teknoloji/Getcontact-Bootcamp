package com.gtc.getcamp.schedule.domain.model

data class ScheduleModel(
    val scheduleId: Int,
    val title: String,
    val description: String?,
    val date: String?,
    val hours: String?,
    val platform: String?,
    val isBookmarked: Boolean?,
    val topics: List<String>?,
    val speakerPerson: SpeakerPersonModel?,
    val imageUrl: String?,
)