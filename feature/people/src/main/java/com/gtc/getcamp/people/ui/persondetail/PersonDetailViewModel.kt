package com.gtc.getcamp.people.ui.persondetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtc.getcamp.people.domain.usecase.GetPersonDetailUseCase
import com.gtc.getcamp.people.ui.persondetail.state.PersonDetailScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailViewModel @Inject constructor(
    private val getPersonDetailUseCase: GetPersonDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _uiState = MutableStateFlow<PersonDetailScreenState>(PersonDetailScreenState.LoadingState)
    val uiState = _uiState.asStateFlow()

    private val personId by lazy {
        savedStateHandle.get<Int>("personId") ?: -1
    }

    init {
        getPersonDetail()
    }

    private fun getPersonDetail() {
        viewModelScope.launch {
            getPersonDetailUseCase.getPerson(personId).collect {person ->
                _uiState.value = PersonDetailScreenState.SuccessState(person = person)
            }
        }
    }
}