package com.tfg.gymapp.presentation.programs

import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.tfg.gymapp.data.model.programDailyContentMap
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun ProgramDetailScreen(
    programName: String,
    navController: NavController,
    viewModel: ProgramProgressViewModel = viewModel()
) {
    val progressMap by viewModel.progress.collectAsState()
    val completedDay = progressMap[programName] ?: 0
    val allDays = programDailyContentMap[programName] ?: emptyList()
    val showByWeeks = programName != "Comer más saludable"

    val totalWeeks = when (programName) {
        "Bajar de peso" -> 4
        "Ganar masa muscular" -> 6
        "Flexibilidad" -> 3
        "Mejorar resistencia" -> 5
        "Tonificar cuerpo" -> 4
        else -> 4
    }

    val expandedWeeks = remember { mutableStateMapOf<Int, Boolean>() }
    val snackbarHostState = remember { SnackbarHostState() }

    val weeksGrouped = List(totalWeeks) { allDays }

    LaunchedEffect(programName) {
        viewModel.loadProgress(programName)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color(0xFFF6FDF6)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color(0xFF1B4332))
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Programa: $programName",
                    style = MaterialTheme.typography.headlineSmall.copy(color = Color(0xFF1B4332))
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (showByWeeks) {
                LazyColumn {
                    items(weeksGrouped.indices.toList()) { index ->
                        val weekNumber = index + 1
                        val weekDays = weeksGrouped[index]
                        val isWeekUnlocked = completedDay >= (weekNumber - 1) * allDays.size

                        Column(modifier = Modifier.padding(vertical = 8.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Semana $weekNumber",
                                    style = MaterialTheme.typography.titleMedium.copy(color = Color(0xFF2D6A4F)),
                                    modifier = Modifier.weight(1f)
                                )
                                TextButton(
                                    onClick = {
                                        expandedWeeks[weekNumber] = !(expandedWeeks[weekNumber] ?: false)
                                    },
                                    enabled = isWeekUnlocked
                                ) {
                                    Text(
                                        if (expandedWeeks[weekNumber] == true) "Ocultar" else "Ver contenido",
                                        color = if (isWeekUnlocked) Color(0xFF40916C) else Color.Gray
                                    )
                                }
                            }

                            AnimatedVisibility(visible = expandedWeeks[weekNumber] == true) {
                                Column(modifier = Modifier.padding(start = 12.dp)) {
                                    weekDays.forEach { activity ->
                                        val isUnlocked = activity.dayNumber <= completedDay + 1
                                        val isCompleted = activity.dayNumber <= completedDay
                                        val textColor = when {
                                            isCompleted -> Color(0xFF2D6A4F)
                                            isUnlocked -> Color.Black
                                            else -> Color.Gray
                                        }

                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 6.dp)
                                                .clickable(enabled = isUnlocked && isWeekUnlocked) {
                                                    navController.navigate("daily_detail/${Uri.encode(programName)}/${activity.dayNumber}")
                                                },
                                            colors = CardDefaults.cardColors(
                                                containerColor = if (isUnlocked && isWeekUnlocked) Color(0xFFE9F5EC) else Color(0xFFF0F0F0)
                                            )
                                        ) {
                                            Column(Modifier.padding(12.dp)) {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Text(
                                                        text = "Día ${activity.dayNumber}: ${activity.title}",
                                                        color = textColor,
                                                        style = MaterialTheme.typography.titleSmall,
                                                        modifier = Modifier.weight(1f)
                                                    )
                                                    when {
                                                        isCompleted -> Text("✅", color = Color(0xFF2D6A4F))
                                                        !isUnlocked -> Text("🔒", color = Color.Gray)
                                                    }
                                                }
                                                Spacer(Modifier.height(4.dp))
                                                Text(
                                                    text = activity.description,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = textColor
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                LazyColumn {
                    items(allDays) { activity ->
                        val isUnlocked = activity.dayNumber <= completedDay + 1
                        val isCompleted = activity.dayNumber <= completedDay
                        val textColor = when {
                            isCompleted -> Color(0xFF2D6A4F)
                            isUnlocked -> Color.Black
                            else -> Color.Gray
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clickable(enabled = isUnlocked) {
                                    val encodedProgramName = URLEncoder.encode(programName, StandardCharsets.UTF_8.toString())
                                    navController.navigate("daily_detail/$encodedProgramName/${activity.dayNumber}")
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = if (isUnlocked) Color(0xFFE9F5EC) else Color(0xFFF0F0F0)
                            )
                        ) {
                            Column(Modifier.padding(12.dp)) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Día ${activity.dayNumber}: ${activity.title}",
                                        color = textColor,
                                        style = MaterialTheme.typography.titleSmall,
                                        modifier = Modifier.weight(1f)
                                    )
                                    when {
                                        isCompleted -> Text("✅", color = Color(0xFF2D6A4F))
                                        !isUnlocked -> Text("🔒", color = Color.Gray)
                                    }
                                }
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = activity.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = textColor
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    val currentEntry by navController.currentBackStackEntryAsState()
    val result = currentEntry?.savedStateHandle?.get<Boolean>("dayCompleted") == true

    LaunchedEffect(result) {
        if (result) {
            viewModel.loadProgress(programName) // Actualiza el progreso desde Firestore
            currentEntry?.savedStateHandle?.set("dayCompleted", false)
        }
    }
}
