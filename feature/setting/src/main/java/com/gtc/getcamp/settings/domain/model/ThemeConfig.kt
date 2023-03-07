package com.gtc.getcamp.settings.domain.model

enum class ThemeConfig(val intValue: Int) {
    FOLLOW_SYSTEM(1),
    LIGHT(2),
    DARK(3);

    companion object {
        fun find(value: Int?): ThemeConfig {
            return values().find { it.intValue == value } ?: kotlin.run { FOLLOW_SYSTEM }
        }
    }
}
