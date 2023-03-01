package com.gtc.getcamp.people.data.repository

import com.gtc.getcamp.people.data.datasource.localdatasource.PeopleLocalDatasource
import com.gtc.getcamp.people.data.datasource.remotedatasource.PeopleRemoteDataSource
import com.gtc.getcamp.people.data.mapper.PersonDtoToPersonEntityMapper
import com.gtc.getcamp.people.data.mapper.PersonEntityToPersonMapper
import com.gtc.getcamp.people.domain.model.PersonModel
import com.gtc.getcamp.people.domain.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val peopleLocalDatasource: PeopleLocalDatasource,
    private val peopleRemoteDataSource: PeopleRemoteDataSource,
    private val personEntityToPersonMapper: PersonEntityToPersonMapper,
    private val personDtoToPersonEntityMapper: PersonDtoToPersonEntityMapper,
) : PeopleRepository {
    override fun getPeople(): Flow<List<PersonModel>> = flow {
        peopleLocalDatasource.getPeople().map { personEntity ->
            personEntityToPersonMapper.map(personEntity)
        }.apply {
            emit(this)
            getRemotePeople()
        }
    }

    private suspend fun getRemotePeople() {
        withContext(Dispatchers.IO){
            peopleRemoteDataSource.getPeople().map { personDto ->
                personDtoToPersonEntityMapper.map(personDto)
            }.apply {
                delay(3000)
                peopleLocalDatasource.insertPeople(this)
            }
        }
    }
}