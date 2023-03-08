package com.gtc.getcamp.schedule.presentation.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.presentation.SchedulePreviewParameter

@Composable
fun ScheduleDetailScreen(
    viewModel: ScheduleDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    when (uiState) {
        LoadingState -> {
            Text(text = "LOADING")
        }
        is SuccessState -> {
            val state = (uiState as SuccessState)
            ScheduleDetailScreenContent(schedule = state.schedule)
        }
        is ErrorState -> {
            Text(text = "ERROR")
        }
    }
}

@Composable
private fun ScheduleDetailScreenContent(
    schedule: ScheduleModel,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        if (schedule.imageUrl.isNullOrEmpty().not()) {
            AsyncImage(
                model = schedule.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
            )
        }
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (schedule.speakerPerson != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AsyncImage(
                        model = schedule.speakerPerson.personImage,
                        contentDescription = null,
                        modifier = Modifier
                            .size(35.dp)
                            .clip(shape = CircleShape),
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = schedule.speakerPerson.personName,
                        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 12.sp),
                    )
                }
            }
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = schedule.title,
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 20.sp),
            )
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = schedule.date.orEmpty(),
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 10.sp),
            )
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = schedule.description.orEmpty(),
                style = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
            )
        }
    }
}

@Preview
@Composable
private fun ScheduleDetailScreenPreview(
    @PreviewParameter(SchedulePreviewParameter::class, limit = 1) schedule: ScheduleModel,
) {
    ScheduleDetailScreenContent(schedule)
}