package com.gtc.getcamp.schedule.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtc.getcamp.navigator.Navigator
import com.gtc.getcamp.schedule.domain.usecase.GetScheduleListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getScheduleListUseCase: GetScheduleListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScheduleScreenState>(LoadingState)
    val uiState = _uiState.asStateFlow()

    init {
        fetchSchedules()
    }

    private fun fetchSchedules() {
        viewModelScope.launch {
            getScheduleListUseCase().collect { _uiState.value = SuccessState(it) }
        }
    }

    fun navigateToDetail(scheduleId: String) {
        return navigator.navigateTo("/schedule/${scheduleId}")
    }

}