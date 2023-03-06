package com.gtc.getcamp.people.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gtc.getcamp.people.domain.model.PersonModel
import com.gtc.getcamp.people.ui.component.NetworkImage
import com.gtc.getcamp.people.ui.component.NetworkImageType

@Composable
fun PeopleItem(
    personModel: PersonModel,
    onClickPerson: (personId: String) -> Unit,
) {

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClickPerson.invoke(personModel.personId)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            NetworkImage(
                url = personModel.personImage,
                networkImageType = NetworkImageType.CircleNetworkImageType(
                    Modifier
                        .padding(8.dp)
                        .size(48.dp)
                )
            )
            Text(text = personModel.personName)
        }
        Divider(
            modifier = Modifier
                .padding(
                    start = 8.dp,
                    end = 8.dp
                )
                .background(Color.Gray)
        )
    }

}