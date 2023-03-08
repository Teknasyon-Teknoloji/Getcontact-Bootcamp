package com.gtc.getcamp.schedule.data.datasource.local

import com.gtc.getcamp.database.ScheduleEntity
import com.gtc.getcamp.database.ScheduleWithPersonEmbed
import kotlinx.coroutines.flow.Flow

interface ScheduleLocalDataSource {

    suspend fun getScheduleList(): Flow<List<ScheduleWithPersonEmbed>>
    suspend fun getScheduleDetail(scheduleId: Int): Flow<ScheduleWithPersonEmbed>
    suspend fun insertScheduleList(schedules: List<ScheduleEntity>)
    suspend fun toggleBookmark(scheduleId: Int)
    suspend fun getBookmarkList(): Flow<List<ScheduleWithPersonEmbed>>
}