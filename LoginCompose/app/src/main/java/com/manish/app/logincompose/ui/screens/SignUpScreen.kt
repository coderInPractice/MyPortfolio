package com.manish.app.logincompose.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun SignUpScreenInflate(modifier: Modifier,
                        navController: NavController,
                        snackbarDelegate: SnackbarDelegate) {
    mSnackbarDelegate = snackbarDelegate
    StaticSignUpArea(navController)
}

@Composable
private fun StaticSignUpArea(navController: NavController) {
    Column (modifier = Modifier.paint(painter = painterResource(id = R.drawable.circle_scatter_haikei),
        contentScale = ContentScale.Crop))  {
        Image(painter = painterResource(id = R.drawable.arrow_back_24),
            contentDescription = "back_btn",
            modifier = Modifier.padding(start = 20.dp, top = 60.dp))

        Text(text = "Register",
            modifier = Modifier.padding(start = 20.dp, top = 80.dp),
            fontSize = 40.sp)

        Text(text = "Create your account",
            modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 20.dp),
            color = Color.LightGray,
            fontSize = 15.sp)

        SignUpArea(navController)
    }
}

@Composable
private fun SignUpArea(navController: NavController) {
    val authViewModel = viewModel(modelClass = AuthViewModel::class)
    var fullName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var repeatPassword by rememberSaveable { mutableStateOf("") }
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
            value = fullName ,
            placeholder = {Text(text = "Full Name")},
            onValueChange = {fullName = it},
            label = {Text(text = "Full Name")})

        OutlinedTextField(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxWidth(),
            value = email ,
            placeholder = {Text(text = "Email")},
            onValueChange = {email = it},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = {Text(text = "Email")})

        OutlinedTextField(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxWidth(),
            value = password ,
            placeholder = {Text(text = "password")},
            onValueChange = {password = it},
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            label = {Text(text = "Password")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(painter = iconRes,
                        contentDescription = "password_visibility_icon" )
                }
            })

        OutlinedTextField(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .fillMaxWidth(),
            value = repeatPassword ,
            placeholder = {Text(text = "Repeat Password")},
            onValueChange = {repeatPassword = it},
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            isError = password != repeatPassword,
            label = {Text(text = "Repeat Password")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(painter = iconRes,
                        contentDescription = "password_visibility_icon" )
                }
            })

        Spacer(modifier = Modifier.height(40.dp))

        Button(onClick = {registerUserForSignUp(fullName,email,password,repeatPassword,
            authViewModel, navController)
            keyboardController?.hide()},
            shape = Shapes().large,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .height(50.dp)){
            Text(text = "Register")
        }

        Spacer(modifier = Modifier.height(80.dp))

        Row (modifier = Modifier.padding(start = 20.dp)) {
            Text(text = "I have an account? ")
            Text(text = "Log in",
                modifier = Modifier.clickable {
                    navController.navigate(route = NavDestinations.LOGIN_SCREEN)
                },
                color = Color.Green)
        }
    }
}

private fun registerUserForSignUp(name:String,
                                  email:String,
                                  password:String,
                                  repeatPassword:String,
                                  authViewModel: AuthViewModel,
                                  navController: NavController) {
    if (password != repeatPassword) {
        Log.d("TAG", "registerUserForSignUp: passwords don't match")
        return
    }

    CoroutineScope(Dispatchers.Main).launch {
        if (authViewModel.signupUser(name, email, password)) {
            Log.d("TAG", "registerUserForSignUp: signup success")
            navController.navigate(NavDestinations.MAIN_SCREEN) {
                popUpTo(NavDestinations.LOGIN_SCREEN) {
                    inclusive = true
                }
            }
            mSnackbarDelegate!!.showSnackbar(
                state = SnackbarState.DEFAULT,
                message = "Signup success",
                duration = SnackbarDuration.Long)
        } else {
            Log.d("TAG", "registerUserForSignUp: signup failed")
            mSnackbarDelegate!!.showSnackbar(
                state = SnackbarState.DEFAULT,
                message = "Signup failed",
                duration = SnackbarDuration.Long)

        }
    }

}