package com.gtc.getcamp.schedule.presentation.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtc.getcamp.navigator.Navigator
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.usecase.GetScheduleListUseCase
import com.gtc.getcamp.schedule.domain.usecase.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getScheduleListUseCase: GetScheduleListUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
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

    fun navigateToDetail(scheduleId: Int) {
        return navigator.navigateTo("/schedule/${scheduleId}")
    }

    fun toggleBookmark(schedule: ScheduleModel) {
        viewModelScope.launch {
            toggleBookmarkUseCase(schedule.scheduleId).collect()
        }
    }

}