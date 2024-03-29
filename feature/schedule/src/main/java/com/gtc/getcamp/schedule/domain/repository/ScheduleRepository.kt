package com.gtc.getcamp.schedule.domain.repository

import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {

    fun getScheduleList(query: String, platform: Platform): Flow<List<ScheduleModel>>
    fun getScheduleDetail(scheduleId: Int): Flow<ScheduleModel>
    fun toggleBookMark(scheduleId: Int): Flow<Unit>
    fun getBookmarks(): Flow<List<ScheduleModel>>
}