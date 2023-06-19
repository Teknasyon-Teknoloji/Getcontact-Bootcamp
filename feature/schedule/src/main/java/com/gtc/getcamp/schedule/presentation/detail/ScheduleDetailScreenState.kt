package com.gtc.getcamp.schedule.presentation.detail

import com.gtc.getcamp.schedule.domain.model.ScheduleModel

sealed interface ScheduleDetailScreenState

object LoadingState : ScheduleDetailScreenState
data class SuccessState(val schedule: ScheduleModel) : ScheduleDetailScreenState
data class ErrorState(val message: String) : ScheduleDetailScreenState