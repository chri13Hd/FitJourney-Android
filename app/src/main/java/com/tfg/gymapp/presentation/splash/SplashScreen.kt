package com.tfg.gymapp.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.tfg.gymapp.navigation.Screen
import kotlinx.coroutines.delay
import com.tfg.gymapp.R // asegúrate de tener un logo en res/drawable

@Composable
fun SplashScreen(navController: NavController) {
    val greenPrimary = Color(0xFF1E5631)

    LaunchedEffect(Unit) {
        delay(4000) // 2 segundos para mostrar logo
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            navController.navigate(Screen.Home.route) {
                popUpTo(0) { inclusive = true }
            }
        } else {
            navController.navigate(Screen.Auth.route) {
                popUpTo(0) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // 🔽 Coloca tu logo aquí (debe estar en res/drawable)
            Image(
                painter = painterResource(id = R.drawable.gym_logo), // ← cambia 'logo' por el nombre real del archivo
                contentDescription = "Logo",
                modifier = Modifier.size(350.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            CircularProgressIndicator(color = greenPrimary)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Cargando...", color = greenPrimary, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
