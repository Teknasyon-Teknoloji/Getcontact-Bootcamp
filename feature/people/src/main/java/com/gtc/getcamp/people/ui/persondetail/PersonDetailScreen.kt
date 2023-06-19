package com.gtc.getcamp.people.ui.persondetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.gtc.getcamp.people.domain.model.PersonModel
import com.gtc.getcamp.people.ui.people.fakedata.PersonDetailScreenFakeData
import com.gtc.getcamp.people.ui.persondetail.state.PersonDetailScreenState

@Composable
fun PersonDetailScreen(
    viewModel: PersonDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    when (val state = uiState) {
        PersonDetailScreenState.LoadingState -> {

        }
        is PersonDetailScreenState.SuccessState -> {
            PersonDetailContent(
                person = state.person
            )
        }
    }
}

@Composable
fun PersonDetailContent(person: PersonModel) {
    Column {
        AsyncImage(
            model = person.personImage,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = person.personName,
            style = TextStyle(fontWeight = FontWeight.Bold),
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = person.personAbout,
            style = TextStyle(fontWeight = FontWeight.Medium),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewPersonDetailContent(
    @PreviewParameter(
        PersonDetailScreenFakeData::class,
    ) person: PersonModel,
) {
    PersonDetailContent(person = person)
}