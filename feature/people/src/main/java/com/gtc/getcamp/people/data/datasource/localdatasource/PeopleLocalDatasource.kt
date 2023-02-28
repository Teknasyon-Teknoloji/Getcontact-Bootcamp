package com.gtc.getcamp.people.data.datasource.localdatasource

import com.gtc.getcamp.database.PersonEntity

interface PeopleLocalDatasource {
    suspend fun getPeople(): List<PersonEntity>
    suspend fun insertPeople(people: List<PersonEntity>)
}