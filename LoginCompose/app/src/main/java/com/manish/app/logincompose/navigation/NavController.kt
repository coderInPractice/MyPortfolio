package com.manish.app.logincompose.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.manish.app.logincompose.ui.commonui.SnackbarDelegate
import com.manish.app.logincompose.ui.screens.LoginScreenInflate
import com.manish.app.logincompose.ui.screens.MainScreen
import com.manish.app.logincompose.ui.screens.SignUpScreenInflate

@Composable
fun Navigator(modifier: Modifier, snackbarDelegate: SnackbarDelegate) {
    val navController = rememberNavController()
    val firebaseAuth = FirebaseAuth.getInstance()

    NavHost(navController = navController,
        startDestination = if (firebaseAuth.currentUser == null)
            NavDestinations.LOGIN_SCREEN else NavDestinations.MAIN_SCREEN) {

        composable(route = NavDestinations.LOGIN_SCREEN) {
            LoginScreenInflate(modifier = modifier, navController, snackbarDelegate)
        }

        composable(route = NavDestinations.SIGNUP_SCREEN) {
            SignUpScreenInflate(modifier = modifier, navController, snackbarDelegate)
        }

        composable(route = NavDestinations.MAIN_SCREEN) {
            MainScreen(snackbarDelegate, navController)

        }

    }

}