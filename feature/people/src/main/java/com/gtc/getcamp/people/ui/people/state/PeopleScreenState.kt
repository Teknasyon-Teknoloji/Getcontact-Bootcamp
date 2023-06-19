package com.gtc.getcamp.people.ui.people.state

import com.gtc.getcamp.people.domain.model.PersonModel

sealed class PeopleScreenState {
    object LoadingState : PeopleScreenState()
    data class SuccessState(val people: List<PersonModel>) : PeopleScreenState()
}
