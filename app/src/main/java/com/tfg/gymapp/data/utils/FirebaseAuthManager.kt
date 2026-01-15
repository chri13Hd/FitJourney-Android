package com.tfg.gymapp.data.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object FirebaseAuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun registerWithEmail(
        email: String,
        password: String,
        onComplete: (FirebaseUser?) -> Unit,
        onError: (Exception) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                onComplete(result.user)
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }

    fun loginWithEmail(
        email: String,
        password: String,
        onComplete: (FirebaseUser?) -> Unit,
        onError: (Exception) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                onComplete(result.user)
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }

    fun signInAnonymously(
        onComplete: (FirebaseUser?) -> Unit,
        onError: (Exception) -> Unit
    ) {
        auth.signInAnonymously()
            .addOnSuccessListener { result ->
                onComplete(result.user)
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }

    fun getCurrentUser(): FirebaseUser? = auth.currentUser
}
