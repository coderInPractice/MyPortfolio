package com.manish.app.dailydiary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manish.app.dailydiary.R

class FolderDetailScreen {

    @Composable
    fun TopBarWithBackButton(modifier: Modifier) {
        Column {
            Image(painter = painterResource(id = R.drawable.arrow_back_24),
                contentDescription = "back_button",
                modifier = Modifier
                    .padding(top = 64.dp, start = 20.dp))

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "personal notes",
                modifier = Modifier.padding(start = 20.dp),
                fontSize = 35.sp)

            Spacer(modifier = Modifier.height(15.dp))

            FilterChipExample()
            StaggeredNotesList()

        }
    }

    @Composable
    fun FilterChipExample() {
        var selected by remember { mutableStateOf(false) }

        Row(modifier = Modifier.horizontalScroll(rememberScrollState(200), reverseScrolling = true)) {
            for (nums in 1..4) {
                FilterChip(
                    modifier = Modifier.padding(start = 20.dp),
                    onClick = { selected = !selected },
                    label = {
                        Text("Filter chip")
                    },
                    selected = selected,
                    leadingIcon = if (selected) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )
            }
        }


    }

    @Composable
    fun StaggeredNotesList() {
        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(200.dp),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            content = {
                items(7) {
                    ListItem()
                }
            },
            modifier = Modifier.fillMaxSize())
    }

    @Composable
    private fun ListItem() {
        Card(
            elevation = CardDefaults.cardElevation(),
            modifier = Modifier.padding(start = 10.dp, bottom = 10.dp)
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