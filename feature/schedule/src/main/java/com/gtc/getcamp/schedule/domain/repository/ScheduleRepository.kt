package com.gtc.getcamp.schedule.domain.repository

import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    fun getScheduleList(): Flow<List<ScheduleModel>>
    fun getScheduleDetail(scheduleId: String): Flow<ScheduleModel>
}