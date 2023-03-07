package com.gtc.getcamp.people.ui.people

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.gtc.getcamp.people.domain.model.PersonModel
import com.gtc.getcamp.people.ui.component.PeopleItem
import com.gtc.getcamp.people.ui.people.action.PeopleScreenAction
import com.gtc.getcamp.people.ui.people.fakedata.PeopleScreenFakeData
import com.gtc.getcamp.people.ui.people.state.PeopleScreenState

@Composable
fun PeopleScreen(
    viewModel: PeopleViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        PeopleScreenState.LoadingState -> {

        }
        is PeopleScreenState.SuccessState -> {
            UsersContent(
                people = state.people,
                onClickPerson = {
                    viewModel.onAction(PeopleScreenAction.NavigatePersonDetail(personId = it))
                },
            )
        }
    }
}

@Composable
fun UsersContent(
    people: List<PersonModel>,
    onClickPerson: (personId: Int) -> Unit,
) {
    LazyColumn {
        items(people) {
            PeopleItem(
                personModel = it,
                onClick = onClickPerson,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UsersContentPreview(
    @PreviewParameter(
        PeopleScreenFakeData::class,
    ) people: List<PersonModel>,
) {
    UsersContent(
        people = people,
        onClickPerson = {},
    )
}