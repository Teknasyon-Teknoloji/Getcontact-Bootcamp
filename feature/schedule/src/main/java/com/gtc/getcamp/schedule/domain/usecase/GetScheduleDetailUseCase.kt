package com.gtc.getcamp.schedule.domain.usecase

import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScheduleDetailUseCase  @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    operator fun invoke(scheduleId: Int): Flow<ScheduleModel> =
        scheduleRepository.getScheduleDetail(scheduleId)
}