package com.gtc.getcamp.schedule.data.repository

import com.gtc.getcamp.database.ScheduleEntity
import com.gtc.getcamp.database.ScheduleWithPersonEmbed
import com.gtc.getcamp.schedule.data.datasource.local.ScheduleLocalDataSource
import com.gtc.getcamp.schedule.data.datasource.remote.ScheduleRemoteDataSource
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.model.SpeakerPersonModel
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
                    schedules.map { embed ->
                        mapToScheduleModel(embed)
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
        return channelFlow {
            launch {
                scheduleLocalDataSource.getScheduleDetail(scheduleId).collect { embed ->
                    mapToScheduleModel(embed).apply {
                        send(this)
                    }
                }
            }
        }
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
                speakerPersonId = scheduleDto.speakerId,
                imageUrl = scheduleDto.imageUrl,
            )
        }.apply {
            emit(this)
        }
    }

    private fun mapToScheduleModel(embed: ScheduleWithPersonEmbed): ScheduleModel {
        return ScheduleModel(
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
            },
            imageUrl = embed.schedule.imageUrl
        )
    }
}