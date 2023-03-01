package com.gtc.getcamp.people.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtc.getcamp.people.domain.usecase.GetPeopleUseCase
import com.gtc.getcamp.people.ui.state.PeopleScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val getPeopleUseCase: GetPeopleUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PeopleScreenState.LoadingState)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getPeopleUseCase.getUsers().collect{
                Log.d("","")
            }
        }
    }
}