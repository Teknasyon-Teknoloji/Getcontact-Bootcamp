package com.gtc.getcamp.people.ui.people.action

sealed class PeopleScreenAction {
    data class NavigatePersonDetail(val personId: String) : PeopleScreenAction()
}
