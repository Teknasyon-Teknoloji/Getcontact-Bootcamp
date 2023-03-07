package com.gtc.getcamp.schedule.domain.usecase

import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.repository.ScheduleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetScheduleListUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository,
) {
    operator fun invoke(platform: String?): Flow<List<ScheduleModel>> {
        return scheduleRepository.getScheduleList()
            .map { list ->
                list.filter { item -> item.platform == platform }
            }
    }
}