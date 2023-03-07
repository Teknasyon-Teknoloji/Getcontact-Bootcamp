package com.gtc.getcamp.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity("schedule")
data class ScheduleEntity(
    @PrimaryKey val scheduleId: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "hours") val hours: String?,
    @ColumnInfo(name = "platform") val platform: String?,
    @ColumnInfo(name = "isBookmarked") val isBookmarked: Boolean?,
    @ColumnInfo(name = "topics") val topics: List<String>?,
    @ColumnInfo(name = "speakerPersonId") val speakerPersonId: Int?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
)

data class ScheduleWithPersonEmbed(
    @Embedded
    var schedule: ScheduleEntity,
    @Relation(parentColumn = "speakerPersonId", entityColumn = "personId")
    var person: PersonEntity?
)