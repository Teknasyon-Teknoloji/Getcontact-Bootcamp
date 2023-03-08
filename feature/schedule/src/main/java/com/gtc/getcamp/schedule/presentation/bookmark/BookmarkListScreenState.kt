package com.gtc.getcamp.schedule.presentation.bookmark

import com.gtc.getcamp.schedule.domain.model.ScheduleModel

sealed interface BookmarkListScreenState

object LoadingState : BookmarkListScreenState
data class SuccessState(val schedules: List<ScheduleModel>) : BookmarkListScreenState
data class ErrorState(val message: String) : BookmarkListScreenState
