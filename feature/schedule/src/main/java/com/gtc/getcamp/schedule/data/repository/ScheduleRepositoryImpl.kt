package com.gtc.getcamp.schedule.data.repository

import com.gtc.getcamp.network.api.schedule.ScheduleDto
import com.gtc.getcamp.schedule.data.datasource.local.ScheduleLocalDataSource
import com.gtc.getcamp.schedule.data.datasource.remote.ScheduleRemoteDataSource
import com.gtc.getcamp.schedule.data.mapper.toScheduleEntities
import com.gtc.getcamp.schedule.data.mapper.toScheduleModel
import com.gtc.getcamp.schedule.data.mapper.toScheduleModels
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.repository.Platform
import com.gtc.getcamp.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleLocalDataSource: ScheduleLocalDataSource,
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource
) : ScheduleRepository {

    override fun getScheduleList(query: String, platform: Platform): Flow<List<ScheduleModel>> =
        flow {
            val stream = scheduleLocalDataSource.getScheduleList(query, platform.toString())
                .map { schedules ->
                    if (schedules.isEmpty()) getListFromRemote().insertToLocal()
                    schedules.toScheduleModels()
                }
            emitAll(stream)
        }.flowOn(Dispatchers.IO)

    override fun getBookmarks(): Flow<List<ScheduleModel>> = flow {
        emitAll(scheduleLocalDataSource.getBookmarkList().map { it.toScheduleModels() })
    }.flowOn(Dispatchers.IO)

    override fun getScheduleDetail(scheduleId: Int): Flow<ScheduleModel> {
        return channelFlow {
            launch {
                scheduleLocalDataSource.getScheduleDetail(scheduleId).collect { embed ->
                    embed.toScheduleModel().apply {
                        send(this)
                    }
                }
            }
        }
    }

    override fun toggleBookMark(scheduleId: Int): Flow<Unit> = flow<Unit> {
        scheduleLocalDataSource.toggleBookmark(scheduleId)
    }.flowOn(Dispatchers.IO)

    private suspend fun List<ScheduleDto>.insertToLocal() =
        scheduleLocalDataSource.insertScheduleList(toScheduleEntities())

    private suspend fun getListFromRemote(): List<ScheduleDto> =
        scheduleRemoteDataSource.getSchedule()

}