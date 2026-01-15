package com.tfg.gymapp.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tfg.gymapp.navigation.Screen
import com.tfg.gymapp.ui.theme.GreenDark
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val greenPrimary = Color(0xFF1E5631)
    val backgroundColor = Color(0xFFFAF9F0)
    val cardBackground = Color.White
    val redLogout = Color(0xFF707070)

    var name by remember { mutableStateOf("") }
    var goals by remember { mutableStateOf<List<String>>(emptyList()) }
    var mealPlan by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        uid?.let {
            val snapshot = FirebaseFirestore.getInstance().collection("Users").document(it).get().await()
            name = snapshot.getString("name") ?: ""
            goals = snapshot.get("goals") as? List<String> ?: emptyList()
            mealPlan = snapshot.getString("mealPlan") ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Perfil", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GreenDark,
                    titleContentColor = Color.White
                )
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .verticalScroll(rememberScrollState())
                .background(backgroundColor),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Nombre y saludo
            Text("Hola, $name", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = greenPrimary)

            // Objetivos
            if (goals.isNotEmpty()) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBackground),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Objetivos", fontWeight = FontWeight.Bold, color = greenPrimary, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        goals.forEach {
                            Text("• $it", fontSize = 15.sp)
                        }
                    }
                }
            }

            // Plan de comidas
            if (mealPlan.isNotBlank()) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = cardBackground),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Plan de comidas", fontWeight = FontWeight.Bold, color = greenPrimary, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(mealPlan, fontSize = 15.sp)
                    }
                }
            }

            // Botones tipo sección
            ProfileOptionButton(
                icon = Icons.Default.Person,
                label = "Mis datos",
                onClick = {
                    navController.navigate(Screen.MyDetail.route)
                }
            )

            ProfileOptionButton(
                icon = Icons.Default.Settings,
                label = "Configuración",
                onClick = {
                    navController.navigate(Screen.Settings.route)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de logout
            Button(
                onClick = {
                    FirebaseAuth.getInstance().signOut()
                    navController.navigate("auth") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = redLogout)
            ) {
                Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Cerrar sesión", tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Cerrar sesión", color = Color.White)
            }
        }
    }
}

@Composable
fun ProfileOptionButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, onClick: () -> Unit) {
    val greenPrimary = Color(0xFF1E5631)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = greenPrimary)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = label, fontWeight = FontWeight.Medium, fontSize = 16.sp)
        }
    }
}
