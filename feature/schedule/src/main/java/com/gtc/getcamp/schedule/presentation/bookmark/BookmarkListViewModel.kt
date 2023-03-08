package com.gtc.getcamp.schedule.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gtc.getcamp.navigator.Navigator
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.usecase.GetBookmarkListUseCase
import com.gtc.getcamp.schedule.domain.usecase.ToggleBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkListViewModel @Inject constructor(
    private val navigator: Navigator,
    private val getBookmarkListUseCase: GetBookmarkListUseCase,
    private val toggleBookmarkUseCase: ToggleBookmarkUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<BookmarkListScreenState>(LoadingState)
    val uiState = _uiState.asStateFlow()

    init {
        fetchBookmarks()
    }

    private fun fetchBookmarks() {
        viewModelScope.launch {
            getBookmarkListUseCase().collect { _uiState.value = SuccessState(it) }
        }
    }

    fun toggleBookmark(schedule: ScheduleModel) {
        viewModelScope.launch {
            toggleBookmarkUseCase(schedule.scheduleId).collect()
        }
    }

}