package com.manish.app.dailydiary.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.manish.app.dailydiary.R

class NotesDetailScreen {

    @Composable
    fun NotesDetailTopBar(modifier: Modifier) {
        Row (modifier = Modifier.padding(top = 64.dp)) {
            Image(painter = painterResource(id = R.drawable.arrow_back_24),
                contentDescription = "back_button",
                modifier = Modifier.padding(start = 20.dp)
                )

            Image(painter = painterResource(id = R.drawable.push_pin_24),
                contentDescription = "back_button",
                modifier = Modifier.padding(start = 280.dp)
            )

            Image(painter = painterResource(id = R.drawable.more_vert_24),
                contentDescription = "back_button",
                modifier = Modifier.weight(0.4f).padding(end = 10.dp),
                alignment = Alignment.BottomEnd
            )
        }
    }
}