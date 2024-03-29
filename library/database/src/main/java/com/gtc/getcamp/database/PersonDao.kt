package com.gtc.getcamp.database

import androidx.room.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.internal.ChannelFlow

@Dao
interface PersonDao {
    @Query("SELECT * FROM person ORDER BY name")
    fun getAll(): Flow<List<PersonEntity>>

    @Query("SELECT * FROM person WHERE personId LIKE :personId")
    fun findById(personId: Int): Flow<PersonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(person: PersonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(persons: List<PersonEntity>)

    @Delete
    fun delete(person: PersonEntity)
}