package com.manish.app.logincompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Shapes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.manish.app.logincompose.R
import com.manish.app.logincompose.businesslogic.viewmodel.AuthViewModel
import com.manish.app.logincompose.navigation.NavDestinations
import com.manish.app.logincompose.ui.commonui.SnackbarDelegate
import com.manish.app.logincompose.ui.commonui.SnackbarState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private var mSnackbarDelegate: SnackbarDelegate? = null
@Composable
fun LoginScreenInflate(modifier: Modifier,
                       navController: NavController,
                       snackbarDelegate: SnackbarDelegate) {
    mSnackbarDelegate = snackbarDelegate
    StaticArea(navController)
}

@Composable
private fun StaticArea(navController: NavController) {
    Column (modifier = Modifier
        .paint(
            painter = painterResource(id = R.drawable.circle_scatter_haikei),
            contentScale = ContentScale.Crop
        )
        .wrapContentHeight())  {

        Text(text = "Sign in to your Account",
            modifier = Modifier.padding(start = 20.dp, top = 80.dp),
            lineHeight = 40.sp,
            fontSize = 40.sp)

        Text(text = "Sign in to your Account",
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 20.dp),
            color = Color.LightGray,
            fontSize = 15.sp)

        LoginArea(navController)
    }
}

@Composable
private fun LoginArea(navController: NavController) {
    val authViewModel = viewModel(modelClass = AuthViewModel::class)
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val iconRes = if (passwordVisibility)
        painterResource(id = R.drawable.design_ic_visibility)
    else
        painterResource(id = R.drawable.design_ic_visibility_off)

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.White, RectangleShape)
            .fillMaxHeight()) {

        OutlinedTextField(
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            value = email,
            placeholder = { Text(text = "Enter your email") },
            onValueChange = { email = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text(text = "Email") })

        OutlinedTextField(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxWidth(),
            value = password,
            placeholder = { Text(text = "Enter your password") },
            onValueChange = { password = it },
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            label = { Text(text = "Password")},
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(painter = iconRes,
                        contentDescription = "password_visibility_icon" )
                }
            })

        Text(text = "Forgot Password?",
            color = Color.Green,
            modifier = Modifier
                .align(Alignment.End)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .clickable {
                    mSnackbarDelegate!!.showSnackbar(
                        state = SnackbarState.ERROR,
                        message = "Feature is not implemented yet",
                        duration = SnackbarDuration.Long
                    )
                })

        Spacer(modifier = Modifier.height(40.dp))


        Button(
            onClick = {loginWithEmailAndPassword(email,
                password, authViewModel, navController)
                keyboardController?.hide()},
            shape = Shapes().large,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(60.dp))

        LoginWithText()

        GoogleAndFacebookButtons()

        Spacer(modifier = Modifier.height(100.dp))

        Row (modifier = Modifier.padding(start = 20.dp)) {
            Text(text = "Don't have an account? ")
            Text(text = "Register",
                modifier = Modifier.clickable {
                    navController.navigate(route = NavDestinations.SIGNUP_SCREEN)
                },
                color = Color.Green)
        }

    }
}

@Composable
private fun LoginWithText() {
    Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
        Box(modifier = Modifier
            .size(width = 50.dp, 2.dp)
            .background(Color.LightGray)
            .weight(.3f)
            .align(Alignment.CenterVertically))

        Text(text = "Or login with",
            color = Color.Black,
            modifier = Modifier
                .weight(.3f)
                .wrapContentSize()
                .background(Color.Transparent))

        Box(modifier = Modifier
            .size(width = 50.dp, 2.dp)
            .background(Color.LightGray)
            .weight(.3f)
            .align(Alignment.CenterVertically))

    }

}

@Composable
private fun GoogleAndFacebookButtons() {
    Row {
        OutlinedButton(
            onClick = { mSnackbarDelegate!!.showSnackbar(
                state = SnackbarState.ERROR,
                message = "Feature is not implemented yet",
                duration = SnackbarDuration.Long
            ) },
            modifier = Modifier
                .padding(16.dp)
                .weight(.5f)
                .height(50.dp),
            shape = ButtonDefaults.outlinedShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.google),
                contentDescription = "Google Logo",
                modifier = Modifier.size(15.dp)
            )
            Text(text = "Google", modifier = Modifier.padding(start = 8.dp))
        }

        OutlinedButton(
            onClick = {mSnackbarDelegate!!.showSnackbar(
                state = SnackbarState.ERROR,
                message = "Feature is not implemented yet",
                duration = SnackbarDuration.Long
            )  },
            modifier = Modifier
                .padding(16.dp)
                .weight(.5f)
                .height(50.dp),
            shape = ButtonDefaults.outlinedShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.facebook),
                contentDescription = "Facebook Logo",
                modifier = Modifier.size(15.dp)
            )
            Text(text = "Facebook", modifier = Modifier.padding(start = 8.dp))
        }
    }
}

private fun loginWithEmailAndPassword(email:String,
                                      password:String,
                                      authViewModel: AuthViewModel,
                                      navController: NavController) {
    CoroutineScope(Dispatchers.Main).launch {
        if (authViewModel.loginUser(email, password)) {
            mSnackbarDelegate!!.showSnackbar(
                state = SnackbarState.DEFAULT,
                message = "Login Success",
                duration = SnackbarDuration.Short
            )
            navController.navigate(NavDestinations.MAIN_SCREEN) {
                popUpTo(NavDestinations.LOGIN_SCREEN) {
                    inclusive = true
                }
            }
        } else {
            mSnackbarDelegate!!.showSnackbar(
                state = SnackbarState.ERROR,
                message = "Login failed",
                duration = SnackbarDuration.Long
            )
        }
    }
}
