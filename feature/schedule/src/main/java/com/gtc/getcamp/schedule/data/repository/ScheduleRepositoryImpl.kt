package com.gtc.getcamp.schedule.data.repository

import com.gtc.getcamp.database.ScheduleEntity
import com.gtc.getcamp.database.ScheduleWithPersonEmbed
import com.gtc.getcamp.schedule.data.datasource.local.ScheduleLocalDataSource
import com.gtc.getcamp.schedule.data.datasource.remote.ScheduleRemoteDataSource
import com.gtc.getcamp.schedule.data.mapper.toScheduleEntities
import com.gtc.getcamp.schedule.data.mapper.toScheduleModels
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleLocalDataSource: ScheduleLocalDataSource,
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource
) : ScheduleRepository {

    override fun getScheduleList(): Flow<List<ScheduleModel>> {
        return channelFlow {
            launch {
                scheduleLocalDataSource.getScheduleList().collect { schedules ->
                    schedules.toScheduleModels().apply {
                        send(this)
                    }
                }
            }
            launch {
                getRemoteSchedule().collect { schedules ->
                    scheduleLocalDataSource.insertScheduleList(schedules)
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun getBookmarks(): Flow<List<ScheduleModel>> = flow {
        emitAll(scheduleLocalDataSource.getBookmarkList().map { it.toScheduleModels() })
    }.flowOn(Dispatchers.IO)

    override fun getScheduleDetail(scheduleId: Int): Flow<ScheduleModel> {
        return channelFlow {
            launch {
                scheduleLocalDataSource.getScheduleDetail(scheduleId).collect { embed ->
                    embed.toSheduleModel().apply {
                        send(this)
                    }
                }
            }
        }
    }

    override fun toggleBookMark(scheduleId: Int): Flow<Unit> = flow<Unit> {
        scheduleLocalDataSource.toggleBookmark(scheduleId)
    }.flowOn(Dispatchers.IO)

    private suspend fun getRemoteSchedule(): Flow<List<ScheduleEntity>> = flow {
        scheduleRemoteDataSource.getSchedule().toScheduleEntities().apply {
            emit(this)
        }
    }

}