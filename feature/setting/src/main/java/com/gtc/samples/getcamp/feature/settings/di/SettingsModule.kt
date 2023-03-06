package com.gtc.samples.getcamp.feature.settings.di

import com.gtc.samples.getcamp.feature.settings.data.UserPrefRepositoryImpl
import com.gtc.samples.getcamp.feature.settings.domain.repository.UserPrefRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

    @Singleton
    @Binds
    abstract fun bindUserPrefRepository(
        userPrefRepositoryImpl: UserPrefRepositoryImpl
    ): UserPrefRepository
}