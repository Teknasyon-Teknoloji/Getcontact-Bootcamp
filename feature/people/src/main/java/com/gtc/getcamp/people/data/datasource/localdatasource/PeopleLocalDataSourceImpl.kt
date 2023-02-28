package com.gtc.getcamp.people.data.datasource.localdatasource

import com.gtc.getcamp.database.PersonDao
import com.gtc.getcamp.database.PersonEntity
import javax.inject.Inject

class PeopleLocalDataSourceImpl @Inject constructor(
    private val personDao: PersonDao,
) : PeopleLocalDatasource {

    override suspend fun getPeople(): List<PersonEntity> = personDao.getAll()
    override suspend fun insertPeople(people: List<PersonEntity>) = personDao.insertAll(people)
}