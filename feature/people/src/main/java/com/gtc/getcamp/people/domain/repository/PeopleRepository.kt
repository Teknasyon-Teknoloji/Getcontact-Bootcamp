package com.gtc.getcamp.people.domain.repository

import com.gtc.getcamp.people.domain.model.PersonModel
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {
    fun getPeople(): Flow<List<PersonModel>>
    fun getPersonDetail(personId:String): Flow<PersonModel>
}