package com.gtc.getcamp.people.data.datasource.remotedatasource

import com.gtc.getcamp.network.api.person.PersonApi
import com.gtc.getcamp.network.api.person.PersonDto
import javax.inject.Inject

class PeopleRemoteDataSourceImpl @Inject constructor(
    private val personApi: PersonApi,
) : PeopleRemoteDataSource {

    override suspend fun getPeople(): List<PersonDto> = personApi.getAll()
    override suspend fun getPersonDetail(personId: String): PersonDto = personApi.get(personId)
}