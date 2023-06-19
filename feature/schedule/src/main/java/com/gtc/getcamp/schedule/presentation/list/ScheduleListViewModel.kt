package com.gtc.getcamp.schedule.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtc.getcamp.navigator.Navigator
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.repository.Platform
import com.gtc.getcamp.schedule.domain.usecase.GetScheduleListUseCase
import com.gtc.getcamp.schedule.domain.usecase.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class ScheduleListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getScheduleListUseCase: GetScheduleListUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<ScheduleScreenState>(LoadingState)
    val uiState = _uiState.asStateFlow()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    var platform = Platform.ANDROID

    init {
        viewModelScope.launch {
            _query.debounce(500)
                .distinctUntilChanged()
                .collectLatest { query ->
                    fetchSchedules(query, platform)
                }
        }
    }

    fun selectPlatform(platform: Platform) {
        this.platform = platform
        viewModelScope.launch {
            fetchSchedules(_query.value, platform)
        }
    }

    private suspend fun fetchSchedules(query: String, platform: Platform) {
        getScheduleListUseCase(query, platform).collect { _uiState.value = SuccessState(it) }
    }

    fun search(query: String) {
        _query.value = query
    }

    fun navigateToDetail(scheduleId: Int) {
        navigator.navigateTo("/schedule/${scheduleId}")
    }

    fun toggleBookmark(schedule: ScheduleModel) {
        viewModelScope.launch {
            toggleBookmarkUseCase(schedule.scheduleId).collect()
        }
    }

}