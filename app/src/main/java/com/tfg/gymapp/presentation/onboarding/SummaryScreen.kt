package com.tfg.gymapp.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.tfg.gymapp.navigation.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(navController: NavController, uid: String) {
    val greenPrimary = Color(0xFF1E5631)
    val backgroundColor = Color(0xFFFAF9F0)

    var userData by remember { mutableStateOf<Map<String, Any>?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val doc = FirebaseFirestore.getInstance().collection("Users").document(uid).get().await()
        userData = doc.data
        isLoading = false
    }

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text("Resumen del perfil", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = greenPrimary)
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = greenPrimary)
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Gracias por completar tu perfil",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = greenPrimary
                )
                Text(
                    text = "Aquí tienes un resumen de tu información registrada:",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                val info = listOf(
                    "Sexo" to (userData?.get("gender") ?: ""),
                    "Fecha de nacimiento" to (userData?.get("birthDate") ?: ""),
                    "Peso" to (userData?.get("weight") ?: ""),
                    "Altura" to (userData?.get("height") ?: ""),
                    "Peso objetivo" to (userData?.get("targetWeight") ?: ""),
                    "Objetivos" to ((userData?.get("goals") as? List<*>)?.joinToString(", ") ?: ""),
                    "Plan de comidas" to (userData?.get("mealPlan") ?: "")
                )

                info.forEachIndexed { index, (label, value) ->
                    var visible by remember { mutableStateOf(false) }
                    LaunchedEffect(Unit) {
                        delay(index * 100L)
                        visible = true
                    }

                    AnimatedVisibility(
                        visible = visible,
                        enter = slideInVertically(initialOffsetY = { -30 }) + fadeIn()
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = backgroundColor),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(label, fontWeight = FontWeight.Bold, color = greenPrimary, fontSize = 14.sp)
                                Text(value.toString(), color = Color.DarkGray, fontSize = 16.sp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.navigate(Screen.Home.route) {
                            //popUpTo(0) { inclusive = true }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = greenPrimary)
                ) {
                    Text("Finalizar", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}
