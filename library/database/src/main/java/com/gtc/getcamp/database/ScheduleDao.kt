package com.gtc.getcamp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule")
    fun getAll(): Flow<List<ScheduleWithPersonEmbed>>

    @Query("SELECT * FROM schedule WHERE scheduleId LIKE :scheduleId")
    fun findById(scheduleId: Int): Flow<ScheduleWithPersonEmbed>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(schedule: List<ScheduleEntity>)

    @Delete
    fun delete(schedule: ScheduleEntity)

    @Query("UPDATE schedule SET isBookmarked = NOT isBookmarked WHERE scheduleId = :scheduleId")
    fun toggleBookmark(scheduleId: Int)
}