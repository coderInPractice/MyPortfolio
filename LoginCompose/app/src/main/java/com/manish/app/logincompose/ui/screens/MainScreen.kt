package com.manish.app.logincompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.manish.app.logincompose.navigation.NavDestinations
import com.manish.app.logincompose.ui.commonui.SnackbarDelegate

@Composable
fun MainScreen(snackbarDelegate: SnackbarDelegate,
               navController: NavController) {

    val firebaseAuth = FirebaseAuth.getInstance()

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Welcome to Main Screen",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 20.sp)

        Button(onClick = {firebaseAuth.signOut()
            navController.navigate(route = NavDestinations.LOGIN_SCREEN){
                popUpTo(route = NavDestinations.MAIN_SCREEN){
                    inclusive = true
                }
            }
        },
            modifier = Modifier.padding( 20.dp)) {
            Text(text = "Logout")
        }
    }

}
