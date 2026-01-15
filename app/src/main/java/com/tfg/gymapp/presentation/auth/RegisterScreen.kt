package com.tfg.gymapp.presentation.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tfg.gymapp.data.utils.FirebaseAuthManager
import com.tfg.gymapp.data.FirestoreManager
import com.tfg.gymapp.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordStrong(password: String): Boolean {
        return password.length >= 6
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color(0xFFFAFAF4),
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color(0xFF1E5631)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Crear Cuenta", fontSize = 24.sp, color = Color(0xFF1E5631))

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = if (passwordVisible) "Ocultar" else "Mostrar"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Button(
                    onClick = {
                        if (username.isBlank() || email.isBlank() || password.isBlank()) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Completa todos los campos.")
                            }
                            return@Button
                        }

                        if (!isEmailValid(email)) {
                            scope.launch {
                                snackbarHostState.showSnackbar("Correo no válido.")
                            }
                            return@Button
                        }

                        if (!isPasswordStrong(password)) {
                            scope.launch {
                                snackbarHostState.showSnackbar("La contraseña debe tener al menos 6 caracteres.")
                            }
                            return@Button
                        }

                        isLoading = true

                        FirestoreManager.getUidByUsername(
                            username,
                            onSuccess = {
                                isLoading = false
                                scope.launch {
                                    snackbarHostState.showSnackbar("El nombre de usuario ya está en uso.")
                                }
                            },
                            onError = {
                                // OK: el nombre no existe, registramos
                                FirebaseAuthManager.registerWithEmail(
                                    email, password,
                                    onComplete = { user ->
                                        val uid = user?.uid
                                        if (uid == null) {
                                            isLoading = false
                                            scope.launch {
                                                snackbarHostState.showSnackbar("Error: UID no disponible.")
                                            }
                                            return@registerWithEmail
                                        }

                                        FirestoreManager.saveUserData(
                                            uid = uid,
                                            name = username,
                                            email = email,
                                            onSuccess = {
                                                isLoading = false
                                                navController.navigate(Screen.UserProfileSetup.createRoute(uid)) {
                                                    popUpTo(0) { inclusive = true }
                                                }
                                            },
                                            onError = {
                                                isLoading = false
                                                scope.launch {
                                                    snackbarHostState.showSnackbar("Error al guardar datos: ${it.message}")
                                                }
                                            }
                                        )
                                    },
                                    onError = {
                                        isLoading = false
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Error: ${it.message}")
                                        }
                                    }
                                )
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E5631))
                ) {
                    Text("Registrarse", fontSize = 16.sp, color = Color.White)
                }
            }
        }
    }
}
