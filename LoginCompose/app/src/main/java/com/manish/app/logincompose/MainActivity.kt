package com.manish.app.logincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.manish.app.logincompose.navigation.Navigator
import com.manish.app.logincompose.ui.commonui.SnackbarDelegate
import com.manish.app.logincompose.ui.theme.LoginComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginComposeTheme {
                val snackbarHostState = remember {
                    SnackbarHostState()
                }
                val snackbarDelegate  = SnackbarDelegate()
                snackbarDelegate.apply {
                    this.snackbarHostState = snackbarHostState
                    coroutineScope = rememberCoroutineScope()
                }

                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState) {
                            val bgColor = snackbarDelegate.snackbarBackgroundColor
                            Snackbar(snackbarData = it,
                                containerColor = bgColor,
                                modifier = Modifier.testTag(snackbarDelegate.snackbarTestTag))
                        }
                    }) {innerPadding->
                    Navigator(modifier = Modifier.padding(innerPadding), snackbarDelegate)
                }
            }
        }
    }
}
