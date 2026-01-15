package com.tfg.gymapp.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MealPlanScreen(navController: NavController, uid: String) {
    val greenPrimary = Color(0xFF1E5631)
    val backgroundColor = Color(0xFFFAF9F0)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val mealPlans = listOf(
        "Alimentación equilibrada",
        "Alta en proteínas",
        "Vegana",
        "Vegetariana",
        "Baja en carbohidratos",
        "No tengo preferencia"
    )

    var selectedPlan by remember { mutableStateOf("") }

    val animOffset = remember { Animatable(-200f) }
    LaunchedEffect(Unit) {
        animOffset.animateTo(0f, animationSpec = tween(500))
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = {
                    Text("Plan de comidas", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
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
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp)
                .fillMaxSize()
                .offset(y = animOffset.value.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "¿Qué tipo de alimentación prefieres?",
                fontSize = 18.sp,
                color = greenPrimary,
                fontWeight = FontWeight.Medium
            )

            mealPlans.forEachIndexed { index, plan ->
                val isVisible = remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    // Delay para efecto en cascada
                    kotlinx.coroutines.delay(index * 100L)
                    isVisible.value = true
                }

                AnimatedVisibility(
                    visible = isVisible.value,
                    enter = slideInVertically(initialOffsetY = { -30 }) + fadeIn(),
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (plan == selectedPlan) greenPrimary else Color.White,
                        border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = plan == selectedPlan,
                                onClick = { selectedPlan = plan }
                            )
                            .padding(vertical = 4.dp)
                    ) {
                        Text(
                            text = plan,
                            modifier = Modifier.padding(16.dp),
                            color = if (plan == selectedPlan) Color.White else greenPrimary,
                            fontSize = 16.sp
                        )
                    }
                }
            }


            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (selectedPlan.isBlank()) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Selecciona un tipo de alimentación.")
                        }
                        return@Button
                    }

                    val data = mapOf("mealPlan" to selectedPlan)

                    FirebaseFirestore.getInstance().collection("Users")
                        .document(uid)
                        .update(data)
                        .addOnSuccessListener {
                            navController.navigate("summary/$uid") {
                                //popUpTo(0) { inclusive = true }
                            }
                        }
                        .addOnFailureListener {
                            scope.launch {
                                snackbarHostState.showSnackbar("Error al guardar: ${it.message}")
                            }
                        }
                },
                enabled = selectedPlan.isNotBlank(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = greenPrimary)
            ) {
                Text("Continuar", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}
