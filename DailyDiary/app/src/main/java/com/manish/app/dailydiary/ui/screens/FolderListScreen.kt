package com.manish.app.dailydiary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manish.app.dailydiary.R

class FolderListScreen {


    @Composable
    fun TopBar(modifier: Modifier = Modifier) {
        Row {
            Text(text = "folders",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(start = 20.dp, top = 64.dp)
                    .weight(1f),
                fontSize = 32.sp)

            Image(painter = painterResource(id = R.drawable.calendar_month_24),
                modifier = Modifier.padding(top = 74.dp, end = 32.dp),
                contentDescription = "sort_icon"
            )

        }
    }

    @Composable
    fun ShowDiaryFolderList() {
        //TODO: Fetch folder data from dB and show in LazyColumn
        LazyColumn(modifier = Modifier.padding(top = 120.dp)) {
            items(20) {
                ListItems()
            }
        }
    }

    @Composable
    private fun ListItems() {
        val paddingModifier  = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
            .fillMaxWidth()
        ListItem(paddingModifier)
    }

    @Composable
    fun ListItem(modifier: Modifier? = Modifier) {
        Card(
            elevation = CardDefaults.cardElevation(),
            modifier = modifier!!,
        ) {
            Column {
                Row {
                    Text(
                        text = "personal notes",
                        modifier = Modifier
                            .padding(10.dp)
                            .alpha(0.5f)
                            .weight(0.85f),
                        fontSize = 12.sp
                    )

                    Image(
                        painter = painterResource(id = R.drawable.keyboard_arrow_right_24),
                        modifier = Modifier
                            .padding(10.dp)
                            .weight(0.15f),
                        contentDescription = "next_arrow"
                    )
                }
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = "25",
                    modifier = Modifier.padding(10.dp),
                    fontSize = 36.sp
                )
            }

        }
    }
}