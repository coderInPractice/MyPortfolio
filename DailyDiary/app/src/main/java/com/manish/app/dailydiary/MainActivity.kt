package com.manish.app.dailydiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.manish.app.dailydiary.ui.screens.FolderListScreen
import com.manish.app.dailydiary.ui.theme.DailyDiaryTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyDiaryTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {FabButton()},
                    floatingActionButtonPosition = FabPosition.End) { innerPadding ->
                    FolderListScreen().TopBar(modifier = Modifier.padding(innerPadding))
                    FolderListScreen().ShowDiaryFolderList()
                }
            }
        }
    }
}

@Composable
fun FabButton() {
    FloatingActionButton(onClick = {},
        modifier = Modifier.size(80.dp),
        elevation = FloatingActionButtonDefaults.elevation(pressedElevation = 10.dp),
        shape = FloatingActionButtonDefaults.smallShape) {
        Icon(painter = painterResource(id = R.drawable.add_24),
            contentDescription = "Add",
            modifier = Modifier
                .padding(4.dp)
                .size(36.dp))

    }
 }
