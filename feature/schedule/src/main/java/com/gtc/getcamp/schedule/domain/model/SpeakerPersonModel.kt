package com.gtc.getcamp.schedule.domain.model

data class SpeakerPersonModel(
    val personId: Int,
    val personName: String,
    val personImage: String,
    val personAbout: String,
    val personLinks: List<String>
)