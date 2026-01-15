package com.tfg.gymapp.presentation.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalSelectionScreen(navController: NavController, uid: String) {
    val greenPrimary = Color(0xFF1E5631)
    val backgroundColor = Color(0xFFFAF9F0)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val allGoals = listOf(
        "Bajar de peso",
        "Ganar masa muscular",
        "Mejorar resistencia",
        "Comer más saludable",
        "Tonificar cuerpo"
    )

    val selectedGoal = remember { mutableStateOf<String?>(null) }

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
                    Text("Objetivos", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
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
                "Selecciona tu objetivo principal:",
                fontSize = 18.sp,
                color = greenPrimary,
                fontWeight = FontWeight.Medium
            )

            allGoals.forEachIndexed { index, goal ->
                val isSelected = selectedGoal.value == goal
                val visible = remember { mutableStateOf(false) }

                LaunchedEffect(Unit) {
                    delay(index * 100L)
                    visible.value = true
                }

                AnimatedVisibility(
                    visible = visible.value,
                    enter = slideInVertically(initialOffsetY = { -30 }) + fadeIn()
                ) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) greenPrimary else Color.White,
                        border = ButtonDefaults.outlinedButtonBorder.copy(width = 1.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .toggleable(
                                value = isSelected,
                                onValueChange = {
                                    selectedGoal.value = if (isSelected) null else goal
                                }
                            )
                            .padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = goal,
                                color = if (isSelected) Color.White else greenPrimary,
                                fontSize = 16.sp
                            )
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Seleccionado",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (selectedGoal.value == null) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Selecciona un objetivo.")
                        }
                        return@Button
                    }

                    val data = mapOf(
                        "goals" to listOf(selectedGoal.value),
                        "selectedGoal" to selectedGoal.value
                    )

                    FirebaseFirestore.getInstance().collection("Users")
                        .document(uid)
                        .update(data)
                        .addOnSuccessListener {
                            navController.navigate("meal_plan/$uid")
                        }
                        .addOnFailureListener {
                            scope.launch {
                                snackbarHostState.showSnackbar("Error al guardar: ${it.message}")
                            }
                        }
                },
                enabled = selectedGoal.value != null,
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
