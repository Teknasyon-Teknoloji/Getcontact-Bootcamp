package com.gtc.getcamp.schedule.presentation

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.gtc.getcamp.schedule.domain.model.ScheduleModel
import com.gtc.getcamp.schedule.domain.model.SpeakerPersonModel

class SchedulePreviewParameter : PreviewParameterProvider<ScheduleModel> {

    override val values: Sequence<ScheduleModel>
        get() = sequenceOf(
            ScheduleModel(
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
            ScheduleModel(
                2,
                "Hilt",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore",
                date = "Now",
                hours = "16:00 - 17:00",
                platform = "Android",
                isBookmarked = false,
                topics = listOf(),
                speakerPerson = SpeakerPersonModel(
                    personId = 20,
                    personName = "Ömer Aksu",
                    personImage = "https://ca.slack-edge.com/T02RLGEPC-U8TNMBJHL-dd8ca3a0d0a3-512",
                    personAbout = "HERE IS A PRESENTATION",
                    personLinks = listOf()
                ),
                imageUrl = null,
            )
        )
}