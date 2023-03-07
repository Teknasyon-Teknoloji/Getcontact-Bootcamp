package com.gtc.getcamp.people.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.gtc.getcamp.people.domain.model.PersonModel

@Composable
fun PeopleItem(
    personModel: PersonModel,
    onClick: (personId: Int) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick.invoke(personModel.personId)
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = personModel.personImage,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Text(text = personModel.personName)
        }
        Divider(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(Color.Gray)
        )
    }

}