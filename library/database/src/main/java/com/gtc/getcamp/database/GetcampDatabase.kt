package com.gtc.getcamp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.gtc.getcamp.database.typeconverter.DateConverter
import com.gtc.getcamp.database.typeconverter.PersonLinkTypeConverter

@Database(
    entities = [
        PersonEntity::class,
        ScheduleEntity::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    PersonLinkTypeConverter::class,
    DateConverter::class,
)
abstract class GetcampDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
    abstract fun scheduleDao(): ScheduleDao
}