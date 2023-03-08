package com.gtc.getcamp.schedule.presentation.bookmark

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gtc.getcamp.schedule.presentation.list.ListItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookmarkListScreen(
    viewModel: BookmarkListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        LoadingState -> {
            Text(text = "LOADING")
        }
        is SuccessState -> {
            val state = (uiState as SuccessState)
            LazyColumn {
                itemsIndexed(state.schedules) { index, item ->
                    ListItem(
                        item = item,
                        onBookmark = {
                            viewModel.toggleBookmark(item)
                        }
                    )
                    if (index < (state.schedules.size - 1)) {
                        Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 0.5.dp)
                    }
                }
            }
        }
        is ErrorState -> {
            Text(text = "ERROR")
        }
    }
}