package com.gtc.getcamp.people.ui.people

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtc.getcamp.navigator.Navigator
import com.gtc.getcamp.people.domain.usecase.GetPeopleUseCase
import com.gtc.getcamp.people.ui.people.action.PeopleScreenAction
import com.gtc.getcamp.people.ui.people.state.PeopleScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getPeopleUseCase: GetPeopleUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<PeopleScreenState>(PeopleScreenState.LoadingState)
    val uiState = _uiState.asStateFlow()

    init {
        fetchPeople()
    }

    private fun fetchPeople() {
        viewModelScope.launch {
            getPeopleUseCase.getUsers().collect {
                _uiState.value = PeopleScreenState.SuccessState(people = it)
            }
        }
    }

    fun onAction(peopleScreenAction: PeopleScreenAction) {
        when (peopleScreenAction) {
            is PeopleScreenAction.NavigatePersonDetail -> {
                openPeopleDetailScreen(personId = peopleScreenAction.personId)
            }
        }
    }

    private fun openPeopleDetailScreen(personId: Int) {
        navigator.navigateTo("/person/${personId}")
    }
}