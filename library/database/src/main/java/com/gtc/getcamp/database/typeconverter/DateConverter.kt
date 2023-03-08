package com.gtc.getcamp.database.typeconverter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? = date?.time
}