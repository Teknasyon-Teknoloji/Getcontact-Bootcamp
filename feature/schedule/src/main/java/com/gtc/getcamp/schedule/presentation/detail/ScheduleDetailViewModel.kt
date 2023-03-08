package com.gtc.getcamp.schedule.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtc.getcamp.schedule.domain.usecase.GetScheduleDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleDetailViewModel @Inject constructor(
    private val getScheduleDetailUseCase: GetScheduleDetailUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val _uiState = MutableStateFlow<ScheduleDetailScreenState>(LoadingState)
    val
            uiState = _uiState.asStateFlow()

    private val scheduleId by lazy {
        savedStateHandle.get<Int>("scheduleId")
    }

    init {
        getScheduleDetail()
    }

    private fun getScheduleDetail() {
        viewModelScope.launch {
            getScheduleDetailUseCase(scheduleId.toString()).collect { schedule ->
                _uiState.value = SuccessState(schedule = schedule)
            }
        }
    }
}