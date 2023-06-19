package com.gtc.getcamp.people.ui.persondetail.state

import com.gtc.getcamp.people.domain.model.PersonModel

sealed class PersonDetailScreenState {
    object LoadingState : PersonDetailScreenState()
    data class SuccessState(val person: PersonModel) : PersonDetailScreenState()
}