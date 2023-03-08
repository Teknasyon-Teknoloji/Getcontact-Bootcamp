package com.gtc.getcamp.schedule.data.datasource.local

import com.gtc.getcamp.database.ScheduleDao
import com.gtc.getcamp.database.ScheduleEntity
import com.gtc.getcamp.database.ScheduleWithPersonEmbed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ScheduleLocalDataSourceImpl @Inject constructor(
    private val scheduleDao: ScheduleDao
) : ScheduleLocalDataSource {

    override suspend fun getScheduleList(): Flow<List<ScheduleWithPersonEmbed>> {
        return scheduleDao.getAll()
    }

    override suspend fun getScheduleDetail(scheduleId: Int): Flow<ScheduleWithPersonEmbed> {
        return scheduleDao.findById(scheduleId)
    }

    override suspend fun insertScheduleList(schedules: List<ScheduleEntity>) {
        return scheduleDao.insertAll(schedules)
    }

    override suspend fun toggleBookmark(scheduleId: Int) {
        scheduleDao.toggleBookmark(scheduleId)
    }

    override suspend fun getBookmarkList(): Flow<List<ScheduleWithPersonEmbed>> =
        scheduleDao.getBookmarks()
}