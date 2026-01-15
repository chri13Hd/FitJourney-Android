package com.tfg.gymapp.presentation.profile

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tfg.gymapp.data.utils.scheduleDailyNotification
import com.tfg.gymapp.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    val context = LocalContext.current
    var notificationsEnabled by remember { mutableStateOf(true) }
    val greenPrimary = Color(0xFF1E5631)
    val lightBackground = Color(0xFFFAF9F0)

    var showDialog by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }

    val user = FirebaseAuth.getInstance().currentUser
    val email = user?.email
    val uid = user?.uid

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración", fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = greenPrimary,
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(lightBackground)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Card de notificaciones push
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Notificaciones Push", fontSize = 16.sp, color = Color.DarkGray)
                    Switch(
                        checked = notificationsEnabled,
                        onCheckedChange = {
                            notificationsEnabled = it
                            if (it) {
                                scheduleDailyNotification(context)
                                Toast.makeText(context, "Notificación diaria activada", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "Notificación diaria desactivada", Toast.LENGTH_SHORT).show()
                            }
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = greenPrimary
                        )
                    )
                }
            }

            // Botón para eliminar cuenta
            Button(
                onClick = { showDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text("Eliminar cuenta", color = Color.White, fontSize = 16.sp)
            }
        }
    }

    // Modal estilizado acorde al logo
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = greenPrimary,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "¿Estás seguro?",
                        style = MaterialTheme.typography.titleLarge.copy(color = greenPrimary)
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Esta acción eliminará tu cuenta y todos tus datos de Fit Journey.",
                        color = Color.DarkGray,
                        fontSize = 14.sp
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Confirma tu contraseña") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = greenPrimary,
                            cursorColor = greenPrimary
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (email != null && password.isNotBlank() && uid != null) {
                            val credential = EmailAuthProvider.getCredential(email, password)
                            user?.reauthenticate(credential)?.addOnCompleteListener { authTask ->
                                if (authTask.isSuccessful) {
                                    val db = FirebaseFirestore.getInstance()
                                    db.collection("Users").document(uid).delete()
                                    db.collection("UserProgress").document(uid).delete()

                                    user.delete().addOnCompleteListener { deleteTask ->
                                        if (deleteTask.isSuccessful) {
                                            Toast.makeText(context, "Cuenta y datos eliminados", Toast.LENGTH_LONG).show()
                                            navController.navigate(Screen.Auth.route) {
                                                popUpTo(0) { inclusive = true }
                                            }
                                        } else {
                                            Toast.makeText(context, "Error al eliminar cuenta", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                } else {
                                    Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_LONG).show()
                                }
                            }
                        } else {
                            Toast.makeText(context, "Introduce tu contraseña", Toast.LENGTH_LONG).show()
                        }
                        showDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = greenPrimary)
                ) {
                    Text("Eliminar cuenta", color = Color.White)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar", color = Color.Gray)
                }
            },
            containerColor = lightBackground,
            shape = RoundedCornerShape(20.dp)
        )
    }
}
