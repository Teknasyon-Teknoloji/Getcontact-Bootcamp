package com.gtc.getcamp.network.di

import com.gtc.getcamp.network.api.person.PersonApi
import com.gtc.getcamp.network.api.schedule.ScheduleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun providePersonApi(retrofit: Retrofit): PersonApi = retrofit.create()

    @Provides
    @Singleton
    fun provideScheduleApi(retrofit: Retrofit): ScheduleApi = retrofit.create()

}