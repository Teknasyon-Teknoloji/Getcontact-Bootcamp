package com.gtc.getcamp.people.data.mapper

import com.gtc.getcamp.database.PersonEntity
import com.gtc.getcamp.network.api.person.PersonDto
import javax.inject.Inject

class PersonDtoToPersonEntityMapper @Inject constructor() {
    fun map(personDto: PersonDto) = PersonEntity(
        personId = personDto.id.orEmpty(),
        name = personDto.name.orEmpty(),
        imageUrl = personDto.imageUrl.orEmpty(),
        about = personDto.about.orEmpty(),
        links = personDto.links.orEmpty(),
    )
}