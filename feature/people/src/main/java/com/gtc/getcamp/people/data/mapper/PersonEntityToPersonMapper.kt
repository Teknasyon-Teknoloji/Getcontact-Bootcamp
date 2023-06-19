package com.gtc.getcamp.people.data.mapper

import com.gtc.getcamp.database.PersonEntity
import com.gtc.getcamp.people.domain.model.PersonModel
import javax.inject.Inject

class PersonEntityToPersonMapper @Inject constructor() {
    fun map(personEntity: PersonEntity) = PersonModel(
        personId = personEntity.personId,
        personName = personEntity.name,
        personImage = personEntity.imageUrl.orEmpty(),
        personAbout = personEntity.about.orEmpty(),
        personLinks = personEntity.links
    )
}