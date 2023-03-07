package com.gtc.getcamp.schedule.data.repository

import com.gtc.getcamp.database.ScheduleEntity
import com.gtc.getcamp.schedule.data.datasource.local.ScheduleLocalDataSource
import com.gtc.getcamp.schedule.data.datasource.remote.ScheduleRemoteDataSource
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.model.SpeakerPersonModel
import com.gtc.getcamp.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
                    schedules.map { embed ->
                        ScheduleModel(
                            scheduleId = embed.schedule.scheduleId,
                            title = embed.schedule.title,
                            description = embed.schedule.description,
                            date = embed.schedule.date,
                            hours = embed.schedule.hours,
                            platform = embed.schedule.platform,
                            isBookmarked = embed.schedule.isBookmarked,
                            topics = embed.schedule.topics,
                            speakerPerson = embed.person?.let {
                                SpeakerPersonModel(
                                    personId = it.personId,
                                    personName = it.name,
                                    personImage = it.imageUrl.orEmpty(),
                                    personAbout = it.about.orEmpty(),
                                    personLinks = it.links
                                )
                            }
                        )
                    }.apply {
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

    override fun getScheduleDetail(scheduleId: String): Flow<ScheduleModel> {
        TODO("Not yet implemented")
    }

    private suspend fun getRemoteSchedule(): Flow<List<ScheduleEntity>> = flow {
        scheduleRemoteDataSource.getSchedule().map { scheduleDto ->
            ScheduleEntity(
                scheduleId = scheduleDto.id,
                title = scheduleDto.title.orEmpty(),
                description = scheduleDto.description,
                date = scheduleDto.date.toString(),
                hours = scheduleDto.hours,
                platform = scheduleDto.platform,
                isBookmarked = false,
                topics = scheduleDto.topics,
                speakerPersonId = scheduleDto.speaker?.id.orEmpty()
            )
        }.apply {
            emit(this)
        }
    }

}