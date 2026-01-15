package com.tfg.gymapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDetailsScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val db = FirebaseFirestore.getInstance()
    val scope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var gender by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(true) }
    var message by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }

    val backgroundColor = Color(0xFFFAF9F0)

    LaunchedEffect(Unit) {
        user?.uid?.let { uid ->
            db.collection("Users").document(uid).get().addOnSuccessListener {
                name = it.getString("name") ?: ""
                gender = it.getString("gender") ?: ""
                birthDate = it.getString("birthDate") ?: ""
                loading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Datos", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E5631),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Text("Nombre", fontSize = 14.sp)
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        nameError = it.isBlank()
                    },
                    isError = nameError,
                    modifier = Modifier.fillMaxWidth(),
                    supportingText = {
                        if (nameError) Text("Este campo no puede estar vacío", color = MaterialTheme.colorScheme.error)
                    }
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Email", fontSize = 14.sp)
                OutlinedTextField(
                    value = email,
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Género", fontSize = 14.sp)
                OutlinedTextField(
                    value = gender,
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Fecha de nacimiento", fontSize = 14.sp)
                OutlinedTextField(
                    value = birthDate,
                    onValueChange = {},
                    enabled = false,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                ClickableText(
                    text = AnnotatedString("¿Quieres cambiar tu contraseña? Pulsa aquí."),
                    onClick = {
                        user?.email?.let { email ->
                            auth.sendPasswordResetEmail(email)
                            message = "Se envió un correo para cambiar la contraseña."
                        }
                    },
                    modifier = Modifier.padding(vertical = 8.dp),
                    style = LocalTextStyle.current.copy(color = Color(0xFF1E5631))
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        nameError = name.isBlank()
                        if (!nameError) {
                            scope.launch {
                                try {
                                    user?.uid?.let { uid ->
                                        db.collection("Users").document(uid).update(
                                            mapOf("name" to name)
                                        )
                                    }
                                    message = "Datos actualizados correctamente"
                                } catch (e: Exception) {
                                    message = "Error: ${e.message}"
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E5631))
                ) {
                    Text("Guardar Cambios", color = Color.White)
                }

                if (message.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(message, color = Color.DarkGray)
                }
            }
        }
    }
}
