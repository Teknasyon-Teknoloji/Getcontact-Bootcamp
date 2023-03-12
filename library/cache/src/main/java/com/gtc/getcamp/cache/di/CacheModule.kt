package com.gtc.getcamp.cache.di

import com.gtc.getcamp.cache.LocalCacheProvider
import com.gtc.getcamp.cache.LocalCacheProviderImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {
    @Singleton
    @Binds
    abstract fun bindLocalCache(localCacheProviderImp: LocalCacheProviderImp): LocalCacheProvider
}