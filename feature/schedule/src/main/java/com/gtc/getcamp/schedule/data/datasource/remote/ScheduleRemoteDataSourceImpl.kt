package com.gtc.getcamp.schedule.data.datasource.remote

import com.gtc.getcamp.network.api.schedule.ScheduleApi
import com.gtc.getcamp.network.api.schedule.ScheduleDto
import javax.inject.Inject

class ScheduleRemoteDataSourceImpl @Inject constructor(
    private val scheduleApi: ScheduleApi
) : ScheduleRemoteDataSource {

    override suspend fun getSchedule(): List<ScheduleDto> {
        return scheduleApi.getAll()
    }

    override suspend fun getScheduleDetail(scheduleId: Int): ScheduleDto {
        return scheduleApi.get(scheduleId)
    }
}