package com.gtc.getcamp.settings.di

import com.gtc.getcamp.settings.data.UserPrefRepositoryImpl
import com.gtc.getcamp.settings.domain.repository.UserPrefRepository
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