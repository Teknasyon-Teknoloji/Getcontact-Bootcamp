package com.gtc.getcamp.people.domain.model

data class PersonModel(
    val personId: Int,
    val personName: String,
    val personImage: String,
    val personAbout: String,
    val personLinks: List<String>
)