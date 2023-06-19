package com.gtc.getcamp.schedule.data.mapper

import com.gtc.getcamp.database.PersonEntity
import com.gtc.getcamp.database.ScheduleEntity
import com.gtc.getcamp.database.ScheduleWithPersonEmbed
import com.gtc.getcamp.network.api.schedule.ScheduleDto
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.model.SpeakerPersonModel

fun List<ScheduleWithPersonEmbed>.toScheduleModels() =
    map { it.toScheduleModel() }

fun ScheduleWithPersonEmbed.toScheduleModel() =
    ScheduleModel(
        scheduleId = schedule.scheduleId,
        title = schedule.title,
        description = schedule.description,
        date = schedule.date,
        hours = schedule.hours,
        platform = schedule.platform,
        isBookmarked = schedule.isBookmarked,
        topics = schedule.topics,
        speakerPerson = person?.toSpeakerPersonModel(),
        imageUrl = schedule.imageUrl
    )

fun PersonEntity.toSpeakerPersonModel() =
    SpeakerPersonModel(
        personId = personId,
        personName = name,
        personImage = imageUrl.orEmpty(),
        personAbout = about.orEmpty(),
        personLinks = links
    )

fun List<ScheduleDto>.toScheduleEntities() =
    map { it.toScheduleEntity() }

fun ScheduleDto.toScheduleEntity() =
    ScheduleEntity(
        scheduleId = id,
        title = title.orEmpty(),
        description = description,
        date = date,
        hours = hours,
        platform = platform,
        isBookmarked = false,
        topics = topics,
        speakerPersonId = speakerId,
        imageUrl = imageUrl,
    )