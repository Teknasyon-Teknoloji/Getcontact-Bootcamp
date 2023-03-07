package com.gtc.getcamp.schedule.data.datasource.local

import com.gtc.getcamp.database.ScheduleEntity
import com.gtc.getcamp.database.ScheduleWithPersonEmbed
import kotlinx.coroutines.flow.Flow

interface ScheduleLocalDataSource {

    suspend fun getScheduleList(): Flow<List<ScheduleWithPersonEmbed>>
    suspend fun getScheduleDetail(scheduleId: String): Flow<ScheduleWithPersonEmbed>
    suspend fun insertScheduleList(schedules: List<ScheduleEntity>)
}