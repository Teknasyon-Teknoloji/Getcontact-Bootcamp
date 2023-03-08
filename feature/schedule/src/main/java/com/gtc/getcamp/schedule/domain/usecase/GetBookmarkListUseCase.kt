package com.gtc.getcamp.schedule.domain.usecase

import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookmarkListUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    operator fun invoke(): Flow<List<ScheduleModel>> = scheduleRepository.getBookmarks()
}