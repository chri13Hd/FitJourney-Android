package com.tfg.gymapp.data

import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

object FirestoreManager {

    private val db = FirebaseFirestore.getInstance()

    fun saveUserData(
        uid: String,
        name: String,
        email: String,
        role: String = "user",
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        val userMap = hashMapOf(
            "uid" to uid,
            "name" to name,
            "email" to email,
            "role" to role,
            "createdAt" to Date()
        )

        db.collection("Users")
            .document(uid)
            .set(userMap)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { exception -> onError(exception) }
    }

    fun getUserData(
        uid: String,
        onSuccess: (Map<String, Any>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        db.collection("Users")
            .document(uid)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    onSuccess(document.data ?: emptyMap())
                } else {
                    onError(Exception("Documento no encontrado"))
                }
            }
            .addOnFailureListener { exception -> onError(exception) }
    }

    fun getUidByUsername(
        username: String,
        onSuccess: (String) -> Unit,
        onError: (Exception) -> Unit
    ) {
        db.collection("Users")
            .whereEqualTo("name", username)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    val document = result.documents[0]
                    val email = document.getString("email")
                    if (email != null) {
                        onSuccess(email)
                    } else {
                        onError(Exception("No se encontró el correo para este usuario."))
                    }
                } else {
                    onError(Exception("Usuario no encontrado."))
                }
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }

    fun getEmailByUsername(
        username: String,
        onSuccess: (String) -> Unit,
        onError: (Exception) -> Unit
    ) {
        db.collection("Users")
            .whereEqualTo("name", username)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    val document = result.documents[0]
                    val email = document.getString("email")
                    if (email != null) {
                        onSuccess(email)
                    } else {
                        onError(Exception("Correo no encontrado para este usuario."))
                    }
                } else {
                    onError(Exception("Usuario no encontrado."))
                }
            }
            .addOnFailureListener { exception ->
                onError(exception)
            }
    }


}