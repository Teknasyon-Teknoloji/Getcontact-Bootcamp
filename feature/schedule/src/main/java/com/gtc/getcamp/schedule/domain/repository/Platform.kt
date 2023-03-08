package com.gtc.getcamp.schedule.domain.repository

enum class Platform {
    ANDROID {
        override fun toString(): String = "android"
    },
    IOS {
        override fun toString(): String = "ios"
    },
}