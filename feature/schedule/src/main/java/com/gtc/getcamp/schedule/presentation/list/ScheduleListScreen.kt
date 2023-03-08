package com.gtc.getcamp.schedule.presentation.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gtc.getcamp.schedule.R
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.presentation.list.filter.ScheduleFilterScreen

@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class,
)
@Composable
fun ScheduleListScreen(
    viewModel: ScheduleListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val query by viewModel.query.collectAsState()

    var openFilter by remember {
        mutableStateOf(false)
    }

    if (openFilter) {
        ScheduleFilterScreen(
            currentPlatform = viewModel.platform,
            onDismiss = {
                viewModel.selectPlatform(it)
                openFilter = false
            }
        )
    }
    when (uiState) {
        LoadingState -> {
            Text(text = "LOADING")
        }
        is SuccessState -> {
            val state = (uiState as SuccessState)

            val listState = rememberLazyListState()

            val showTopBar by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

            Column {
                AnimatedVisibility(visible = showTopBar) {
                    TopBar(
                        query = query,
                        onFilter = {
                            openFilter = true
                        },
                        onTextChange = { text ->
                            viewModel.search(text)
                        }
                    )
                }
                LazyColumn(state = listState) {
                    itemsIndexed(state.schedules) { index, item ->
                        ListItem(
                            item = item,
                            onBookmark = {
                                viewModel.toggleBookmark(item)
                            },
                            onClick = {
                                viewModel.navigateToDetail(item.scheduleId)
                            },
                        )
                        if (index < (state.schedules.size - 1)) {
                            Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 0.5.dp)
                        }
                    }
                }
            }
        }
        is ErrorState -> {
            Text(text = "ERROR")
        }
    }
}

@ExperimentalMaterial3Api
@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    query: String,
    onFilter: () -> Unit,
    onTextChange: (text: String) -> Unit,
) {
    Row(
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextField(
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent,
                textColor = Color.White,
                placeholderColor = Color.Gray
            ),
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 8.dp, horizontal = 16.dp),
            value = query,
            placeholder = {
                Text(text = "Search...")
            },
            onValueChange = {
                onTextChange(it)
            },
        )
        IconButton(onClick = onFilter) {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun LazyItemScope.ListItem(
    item: ScheduleModel,
    onBookmark: () -> Unit,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .animateItemPlacement()
            .clickable {
                onClick()
            }
    ) {
        AsyncImage(
            model = item.imageUrl ?: item.speakerPerson?.personImage.orEmpty(),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(48.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = item.speakerPerson?.personName.orEmpty(),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = item.description.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        IconButton(onClick = onBookmark) {
            Icon(
                painter = painterResource(
                    if (item.isBookmarked == true) R.drawable.ic_star
                    else R.drawable.ic_star_outline
                ),
                contentDescription = null,
                tint = Color(0xFFFFEB3B)
            )
        }
    }
}