package com.gtc.getcamp.schedule.presentation

import com.gtc.getcamp.schedule.domain.model.ScheduleModel

sealed interface ScheduleScreenState

object LoadingState : ScheduleScreenState
data class SuccessState(val schedules: List<ScheduleModel>) : ScheduleScreenState
data class ErrorState(val message: String) : ScheduleScreenState
