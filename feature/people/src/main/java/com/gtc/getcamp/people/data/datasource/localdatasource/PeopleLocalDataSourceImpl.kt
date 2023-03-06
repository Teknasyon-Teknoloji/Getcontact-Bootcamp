package com.gtc.getcamp.people.data.datasource.localdatasource

import com.gtc.getcamp.database.PersonDao
import com.gtc.getcamp.database.PersonEntity
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.internal.ChannelFlow
import javax.inject.Inject

class PeopleLocalDataSourceImpl @Inject constructor(
    private val personDao: PersonDao,
) : PeopleLocalDatasource {

    override suspend fun getPeople(): Flow<List<PersonEntity>> = personDao.getAll()
    override suspend fun insertPeople(people: List<PersonEntity>) = personDao.insertAll(people)
    override suspend fun getPerson(personId: String): Flow<PersonEntity> = personDao.findById(personId)
    override suspend fun insertPerson(personEntity: PersonEntity) = personDao.insert(personEntity)
}