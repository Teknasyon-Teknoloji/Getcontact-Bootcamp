package com.gtc.getcamp.schedule.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.model.SpeakerPersonModel

@Composable
fun ScheduleListScreen(
    scheduleListViewModel: ScheduleListViewModel = hiltViewModel(),
) {
    val uiState by scheduleListViewModel.uiState.collectAsState()

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
                        onClick = { scheduleListViewModel.navigateToDetail(item.scheduleId.toString()) },
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


@Composable
fun ListItem(
    item: ScheduleModel,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
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
    }
}


@Preview
@Composable
fun PreviewListItem() {
    ListItem(
        item = ScheduleModel(
            1,
            "Modularization",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore",
            date = "Now",
            hours = "14:30 - 15:00",
            platform = "Android",
            isBookmarked = false,
            topics = listOf(),
            speakerPerson = SpeakerPersonModel(
                personId = 10,
                personName = "Alireza",
                personImage = "https://media.licdn.com/dms/image/C4D03AQE8Q6m_811XQA/profile-displayphoto-shrink_100_100/0/1644508106852?e=1681948800&v=beta&t=jrGX935rJ1Z3tD1hYkY8Y-JZD4k7OSA2Muz-xVU0ibU",
                personAbout = "HERE IS A PRESENTATION",
                personLinks = listOf()
            ),
            imageUrl = null,
        ),
        onClick = {}
    )
}