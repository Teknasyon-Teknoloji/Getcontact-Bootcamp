package com.gtc.getcamp.schedule.domain.usecase

import com.gtc.getcamp.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToggleBookmarkUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    operator fun invoke(scheduleId: Int): Flow<Unit> {
        return scheduleRepository.toggleBookMark(scheduleId)
    }
}