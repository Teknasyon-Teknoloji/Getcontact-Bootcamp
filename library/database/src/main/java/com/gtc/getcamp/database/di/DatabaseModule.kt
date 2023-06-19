package com.gtc.getcamp.database.di

import android.content.Context
import androidx.room.Room
import com.gtc.getcamp.database.GetcampDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "gtcCamp-db"

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, GetcampDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun providePersonDao(database: GetcampDatabase) = database.personDao()

    @Provides
    @Singleton
    fun provideScheduleDao(database: GetcampDatabase) = database.scheduleDao()
}