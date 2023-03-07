package com.gtc.getcamp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule")
    fun getAll(): Flow<List<ScheduleWithPersonEmbed>>

    @Query("SELECT * FROM schedule WHERE scheduleId LIKE :scheduleId")
    fun findById(scheduleId: String): Flow<ScheduleWithPersonEmbed>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(schedule: List<ScheduleEntity>)

    @Delete
    fun delete(schedule: ScheduleEntity)
}