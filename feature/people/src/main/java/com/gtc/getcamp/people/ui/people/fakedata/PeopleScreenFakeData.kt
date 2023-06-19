package com.gtc.getcamp.people.ui.people.fakedata

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.gtc.getcamp.people.domain.model.PersonModel

class PeopleScreenFakeData :
    PreviewParameterProvider<List<PersonModel>> {
    override val values: Sequence<List<PersonModel>> = sequenceOf(
        listOf(
            PersonModel(
                personId = 1,
                personName = "GTC Team",
                personImage = "https://cdn.iha.com.tr/Contents/images/2018/32/2599418.jpg",
                personAbout = "GTC Takımıdır",
                personLinks = emptyList()
            ) ,PersonModel(
                personId = 2,
                personName = "GTC Team",
                personImage = "https://cdn.iha.com.tr/Contents/images/2018/32/2599418.jpg",
                personAbout = "GTC Takımıdır",
                personLinks = emptyList()
            ) ,PersonModel(
                personId = 3,
                personName = "GTC Team",
                personImage = "https://cdn.iha.com.tr/Contents/images/2018/32/2599418.jpg",
                personAbout = "GTC Takımıdır",
                personLinks = emptyList()
            ) ,PersonModel(
                personId = 4,
                personName = "GTC Team",
                personImage = "https://cdn.iha.com.tr/Contents/images/2018/32/2599418.jpg",
                personAbout = "GTC Takımıdır",
                personLinks = emptyList()
            ) ,
            PersonModel(
                personId = 5,
                personName = "GTC Team",
                personImage = "https://cdn.iha.com.tr/Contents/images/2018/32/2599418.jpg",
                personAbout = "GTC Takımıdır",
                personLinks = emptyList()
            ),
        ),
    )
}