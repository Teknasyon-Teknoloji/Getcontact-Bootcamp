package com.gtc.getcamp.schedule.presentation.list.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.gtc.getcamp.schedule.domain.repository.Platform

@Composable
fun ScheduleFilterScreen(
    currentPlatform: Platform,
    onDismiss: (platform: Platform) -> Unit = {},
) {
    var platform by remember {
        mutableStateOf(currentPlatform)
    }

    Dialog(
        onDismissRequest = { onDismiss(platform) },
        properties = DialogProperties(
            dismissOnBackPress = true,
        ),
    ) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(color = Color.White)
                    .padding(16.dp)
            ) {
                Text(text = "Current Platform", style = MaterialTheme.typography.headlineMedium)
                Platform.values().forEach {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = platform == it,
                            onClick = {
                                platform = it
                            }
                        )
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}
