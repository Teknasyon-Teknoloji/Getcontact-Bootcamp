package com.gtc.getcamp.people.data.datasource.remotedatasource

import com.gtc.getcamp.network.api.person.PersonDto

interface PeopleRemoteDataSource {
    suspend fun getPeople(): List<PersonDto>
}