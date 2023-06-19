package com.gtc.getcamp.database.typeconverter

import androidx.room.TypeConverter

class PersonLinkTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(",")
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        return list.joinToString { "," }
    }
}