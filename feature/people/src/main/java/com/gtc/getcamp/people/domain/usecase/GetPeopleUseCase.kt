package com.gtc.getcamp.people.domain.usecase

import com.gtc.getcamp.people.domain.model.PersonModel
import com.gtc.getcamp.people.domain.repository.PeopleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository,
) {
     fun getUsers(): Flow<List<PersonModel>> {
        return peopleRepository.getPeople()
    }
}