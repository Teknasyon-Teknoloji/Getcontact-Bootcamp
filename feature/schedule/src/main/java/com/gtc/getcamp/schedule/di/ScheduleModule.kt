package com.gtc.getcamp.schedule.di

import com.gtc.getcamp.database.ScheduleDao
import com.gtc.getcamp.network.api.schedule.ScheduleApi
import com.gtc.getcamp.schedule.data.datasource.local.ScheduleLocalDataSource
import com.gtc.getcamp.schedule.data.datasource.local.ScheduleLocalDataSourceImpl
import com.gtc.getcamp.schedule.data.datasource.remote.ScheduleRemoteDataSource
import com.gtc.getcamp.schedule.data.datasource.remote.ScheduleRemoteDataSourceImpl
import com.gtc.getcamp.schedule.data.repository.ScheduleRepositoryImpl
import com.gtc.getcamp.schedule.domain.repository.ScheduleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ScheduleModule {

    @Provides
    fun provideScheduleLocalDataSource(
        scheduleDao: ScheduleDao
    ): ScheduleLocalDataSource = ScheduleLocalDataSourceImpl(scheduleDao)

    @Provides
    fun provideScheduleRemoteDataSource(
        scheduleApi: ScheduleApi
    ): ScheduleRemoteDataSource = ScheduleRemoteDataSourceImpl(scheduleApi)

    @Provides
    fun provideScheduleRepository(
        scheduleLocalDatasource: ScheduleLocalDataSource,
        scheduleRemoteDataSource: ScheduleRemoteDataSource,
    ): ScheduleRepository = ScheduleRepositoryImpl(
        scheduleLocalDataSource = scheduleLocalDatasource,
        scheduleRemoteDataSource = scheduleRemoteDataSource,
    )
}