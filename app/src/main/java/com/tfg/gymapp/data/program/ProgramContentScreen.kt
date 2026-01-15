package com.tfg.gymapp.data.program

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tfg.gymapp.data.model.programDailyContentMap
import com.tfg.gymapp.presentation.programs.ProgramProgressViewModel

@Composable
fun ProgramContentScreen(
    programName: String,
    navController: NavController,
    viewModel: ProgramProgressViewModel = viewModel()
) {
    val progressState = viewModel.progress.collectAsState()
    val progressMap = progressState.value
    val completedDay = progressMap[programName] ?: 0
    val dailyActivities = programDailyContentMap[programName] ?: emptyList()

    LaunchedEffect(programName) {
        viewModel.loadProgress(programName)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Contenido de \"$programName\"",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(dailyActivities) { activity ->
                val isUnlocked = activity.dayNumber <= completedDay + 1

                Card(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .clickable(enabled = isUnlocked) {
                            val encodedProgram = programName.replace(" ", "%20")

                            if (programName.lowercase().contains("comer más saludable")) {
                                navController.navigate("daily_detail/$encodedProgram/${activity.dayNumber}")
                            } else {
                                navController.navigate("daily_routine/$encodedProgram/${activity.dayNumber}")
                            }
                        },
                    colors = CardDefaults.cardColors(
                        containerColor = if (isUnlocked) MaterialTheme.colorScheme.surface else Color.LightGray
                    )
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            "Día ${activity.dayNumber}: ${activity.title}",
                            style = MaterialTheme.typography.titleMedium,
                            color = if (isUnlocked) MaterialTheme.colorScheme.onSurface else Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            activity.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = if (isUnlocked) MaterialTheme.colorScheme.onSurfaceVariant else Color.Gray
                        )
                        if (!isUnlocked) {
                            Text(
                                text = "🔒 Completa el día anterior para desbloquear",
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}
