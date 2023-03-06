package com.gtc.getcamp.people.di

import com.gtc.getcamp.database.PersonDao
import com.gtc.getcamp.people.data.datasource.localdatasource.PeopleLocalDataSourceImpl
import com.gtc.getcamp.people.data.datasource.localdatasource.PeopleLocalDatasource
import com.gtc.getcamp.people.data.datasource.remotedatasource.PeopleRemoteDataSource
import com.gtc.getcamp.people.data.datasource.remotedatasource.PeopleRemoteDataSourceImpl
import com.gtc.getcamp.people.data.mapper.PersonDtoToPersonEntityMapper
import com.gtc.getcamp.people.data.mapper.PersonEntityToPersonMapper
import com.gtc.getcamp.people.domain.repository.PeopleRepository
import com.gtc.getcamp.people.data.repository.PeopleRepositoryImpl
import com.gtc.getcamp.network.api.person.PersonApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PeopleModule {

    @Provides
    fun providePeopleLocalDataSource(
        personDao: PersonDao,
    ): PeopleLocalDatasource = PeopleLocalDataSourceImpl(personDao = personDao)

    @Provides
    fun providePeopleRemoteDataSource(
        personApi: PersonApi,
    ): PeopleRemoteDataSource = PeopleRemoteDataSourceImpl(personApi = personApi)

    @Provides
    fun providePeopleRepository(
        peopleLocalDatasource: PeopleLocalDatasource,
        peopleRemoteDataSource: PeopleRemoteDataSource,
        peopleEntityToPersonMapper: PersonEntityToPersonMapper,
        personDtoToPersonEntityMapper: PersonDtoToPersonEntityMapper,
    ): PeopleRepository = PeopleRepositoryImpl(
        peopleLocalDatasource = peopleLocalDatasource,
        peopleRemoteDataSource = peopleRemoteDataSource,
        personEntityToPersonMapper = peopleEntityToPersonMapper,
        personDtoToPersonEntityMapper = personDtoToPersonEntityMapper,
    )
}