package com.gtc.getcamp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("person")
data class PersonEntity(
    @PrimaryKey val personId: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_url") val image_url: String?,
    @ColumnInfo(name = "about") val about: String?,
    @ColumnInfo(name = "links") val links: List<String>
)

