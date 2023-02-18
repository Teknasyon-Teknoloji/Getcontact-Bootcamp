package com.gtc.getcamp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAll(): List<PersonEntity>

    @Query("SELECT * FROM person WHERE personId LIKE :personId")
    fun findById(personId: Int): PersonEntity

    @Insert
    fun insert(person: PersonEntity)

    @Insert
    fun insertAll(vararg person: PersonEntity)

    @Delete
    fun delete(person: PersonEntity)
}