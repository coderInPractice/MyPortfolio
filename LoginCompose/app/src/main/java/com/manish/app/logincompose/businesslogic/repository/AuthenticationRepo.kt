package com.manish.app.logincompose.businesslogic.repository

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class AuthenticationRepo {

    private val mAuth = FirebaseAuth.getInstance()

    suspend fun signUpUser(name:String, email:String, password:String) : Boolean {
        return suspendCoroutine { continuation ->
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    continuation.resume(it.user != null)
                }
                .addOnFailureListener {
                    continuation.resume(false)
                    it.stackTrace
                }

        }
    }

    suspend fun loginUser(email:String, password:String) : Boolean {
        return suspendCoroutine { continuation ->
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    continuation.resume(it.user != null)
                }
                .addOnFailureListener {
                    continuation.resume(false)
                    it.stackTrace
                }

        }
    }
}