package com.gtc.getcamp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule")
    fun getAll(): List<ScheduleEntity>

    @Query("SELECT * FROM schedule WHERE scheduleId LIKE :scheduleId")
    fun findById(scheduleId: Int): ScheduleEntity

    @Insert
    fun insert(schedule: ScheduleEntity)

    @Insert
    fun insertAll(vararg schedule: ScheduleEntity)

    @Delete
    fun delete(schedule: ScheduleEntity)
}