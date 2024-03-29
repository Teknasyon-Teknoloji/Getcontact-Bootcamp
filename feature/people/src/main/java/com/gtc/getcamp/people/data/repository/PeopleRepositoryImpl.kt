package com.gtc.getcamp.people.data.repository

import com.gtc.getcamp.database.PersonEntity
import com.gtc.getcamp.people.data.datasource.localdatasource.PeopleLocalDatasource
import com.gtc.getcamp.people.data.datasource.remotedatasource.PeopleRemoteDataSource
import com.gtc.getcamp.people.data.mapper.PersonDtoToPersonEntityMapper
import com.gtc.getcamp.people.data.mapper.PersonEntityToPersonMapper
import com.gtc.getcamp.people.domain.model.PersonModel
import com.gtc.getcamp.people.domain.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val peopleLocalDataSource: PeopleLocalDatasource,
    private val peopleRemoteDataSource: PeopleRemoteDataSource,
    private val personEntityToPersonMapper: PersonEntityToPersonMapper,
    private val personDtoToPersonEntityMapper: PersonDtoToPersonEntityMapper,
) : PeopleRepository {

    override fun getPeople(): Flow<List<PersonModel>> = channelFlow {
        launch {
            peopleLocalDataSource.getPeople().collect { persons ->
                persons.map { person ->
                    personEntityToPersonMapper.map(person)
                }.apply {
                    send(this)
                }
            }
        }
        launch {
            getRemotePeople().collect { persons ->
                peopleLocalDataSource.insertPeople(persons)
            }
        }
    }.flowOn(Dispatchers.IO)

    override fun getPersonDetail(personId: Int): Flow<PersonModel> = channelFlow {
        launch {
            peopleLocalDataSource.getPerson(personId).collect { personEntity ->
                val person = personEntityToPersonMapper.map(personEntity)
                send(person)
            }
        }
        launch {
            getRemotePersonDetail(personId).collect { person ->
                peopleLocalDataSource.insertPerson(person)
            }
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun getRemotePeople(): Flow<List<PersonEntity>> = flow {
        peopleRemoteDataSource.getPeople().map { personDto ->
            personDtoToPersonEntityMapper.map(personDto)
        }.apply {
            emit(this)
        }
    }

    private suspend fun getRemotePersonDetail(personId: Int): Flow<PersonEntity> = flow {
        val person = peopleRemoteDataSource.getPersonDetail(personId)
        val personEntity = personDtoToPersonEntityMapper.map(person)
        emit(personEntity)
    }
}