package com.gtc.getcamp.schedule.data.datasource.remote

import com.gtc.getcamp.network.api.schedule.ScheduleDto

interface ScheduleRemoteDataSource {

    suspend fun getSchedule(): List<ScheduleDto>
    suspend fun getScheduleDetail(scheduleId: String): ScheduleDto
}