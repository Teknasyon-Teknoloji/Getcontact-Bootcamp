package com.gtc.getcamp.people.data.datasource.localdatasource

import com.gtc.getcamp.database.PersonEntity
import kotlinx.coroutines.flow.Flow

interface PeopleLocalDatasource {
    suspend fun getPeople(): Flow<List<PersonEntity>>
    suspend fun insertPeople(people: List<PersonEntity>)
    suspend fun getPerson(personId: String): Flow<PersonEntity>
    suspend fun insertPerson(personEntity: PersonEntity)
}