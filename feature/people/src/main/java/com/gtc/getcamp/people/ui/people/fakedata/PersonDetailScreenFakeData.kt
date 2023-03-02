package com.gtc.getcamp.people.ui.people.fakedata

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.gtc.getcamp.people.domain.model.PersonModel

class PersonDetailScreenFakeData :
    PreviewParameterProvider<PersonModel> {
    override val values: Sequence<PersonModel> = sequenceOf(
            PersonModel(
                personId = "",
                personName = "GTC Team",
                personImage = "https://cdn.iha.com.tr/Contents/images/2018/32/2599418.jpg",
                personAbout = "GTC Takımıdır",
                personLinks = emptyList()
        ),
    )
}