package com.manish.app.logincompose.businesslogic.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manish.app.logincompose.businesslogic.repository.AuthenticationRepo
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AuthViewModel:ViewModel() {

    private val mAuthenticationRepo = AuthenticationRepo()

    suspend fun signupUser(name:String,email:String,password:String) : Boolean {
        val job = viewModelScope.async {
            val signupStatus =  mAuthenticationRepo.signUpUser(name, email,password)
            return@async signupStatus
        }
        return job.await()
    }

    suspend fun loginUser(email:String,password:String) : Boolean {
        val job = viewModelScope.async {
            val loginStatus =  mAuthenticationRepo.loginUser(email,password)
            return@async loginStatus
        }
        return job.await()
    }

}