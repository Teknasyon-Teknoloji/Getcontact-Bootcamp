package com.gtc.getcamp.schedule.presentation.list

import androidx.lifecycle.SavedStateHandle
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
    private val getScheduleListUseCase: GetScheduleListUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScheduleScreenState>(LoadingState)
    val uiState = _uiState.asStateFlow()

    init {
        fetchSchedules(savedStateHandle.get<String>("platform") ?: "android")
    }

    private fun fetchSchedules(platform: String?) {
        viewModelScope.launch {
            getScheduleListUseCase(platform).collect { _uiState.value = SuccessState(it) }
        }
    }

    fun navigateToDetail(scheduleId: String) {
        return navigator.navigateTo("/schedule/${scheduleId}")
    }

}