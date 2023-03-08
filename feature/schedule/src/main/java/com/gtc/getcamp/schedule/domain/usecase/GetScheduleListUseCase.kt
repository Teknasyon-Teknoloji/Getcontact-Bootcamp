package com.gtc.getcamp.schedule.domain.usecase

import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.repository.Platform
import com.gtc.getcamp.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScheduleListUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    operator fun invoke(query: String, platform: Platform): Flow<List<ScheduleModel>> {
        return scheduleRepository.getScheduleList(query, platform)
    }
}