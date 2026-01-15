package com.tfg.gymapp.presentation.routine

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tfg.gymapp.data.model.programRoutineMap
import com.tfg.gymapp.presentation.components.VideoPlayer
import com.tfg.gymapp.presentation.programs.ProgramProgressViewModel
import kotlinx.coroutines.delay

@Composable
fun DailyRoutineScreen(
    programName: String,
    dayNumber: Int,
    navController: NavController,
    viewModel: ProgramProgressViewModel = viewModel()
) {
    val routine = programRoutineMap[programName]?.find { it.dayNumber == dayNumber }
    var index by remember { mutableIntStateOf(0) }
    var completedSets by remember { mutableIntStateOf(0) }
    var started by remember { mutableStateOf(false) }
    var countdown by remember { mutableIntStateOf(3) }
    var isCounting by remember { mutableStateOf(false) }
    var exerciseTimer by remember { mutableIntStateOf(0) }
    var remainingTime by remember { mutableIntStateOf(0) }
    var timerRunning by remember { mutableStateOf(false) }

    val exercise = routine?.exercises?.getOrNull(index)

    fun parseDuration(reps: String): Int {
        return when {
            reps.contains("min") -> {
                Regex("(\\d+)\\s*x?\\s*(\\d+)?\\s*min").find(reps)?.let {
                    val min = it.groupValues[2].ifEmpty { it.groupValues[1] }.toInt()
                    min * 60
                } ?: 60
            }
            reps.contains("seg") -> {
                Regex("(\\d+)\\s*x?\\s*(\\d+)?\\s*seg").find(reps)?.let {
                    val seg = it.groupValues[2].ifEmpty { it.groupValues[1] }.toInt()
                    seg
                } ?: 30
            }
            else -> 60
        }
    }

    fun getSetCount(reps: String): Int {
        return if (reps.contains("x")) {
            Regex("(\\d+)x").find(reps)?.groupValues?.get(1)?.toIntOrNull() ?: 3
        } else {
            1
        }
    }

    val totalSets = exercise?.let { getSetCount(it.reps) } ?: 1

    LaunchedEffect(isCounting) {
        if (isCounting) {
            while (countdown > 0) {
                delay(1000)
                countdown--
            }
            isCounting = false
            started = true
            exercise?.let {
                exerciseTimer = parseDuration(it.reps)
                remainingTime = exerciseTimer
                timerRunning = true
            }
        }
    }

    LaunchedEffect(timerRunning) {
        if (timerRunning) {
            while (remainingTime > 0) {
                delay(1000)
                remainingTime--
            }
            timerRunning = false
        }
    }

    exercise?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9FDF9))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${index + 1}/${routine.exercises.size} - ${it.name}",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color(0xFF1B4332)
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (!started && !isCounting) {
                Text("Pulsa para empezar", color = Color(0xFF1B4332))
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        countdown = 3
                        isCounting = true
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B4332)),
                    modifier = Modifier.size(80.dp)
                ) {
                    Text("▶", color = Color.White, fontSize = 24.sp)
                }
            }

            if (isCounting) {
                Text(
                    text = countdown.toString(),
                    style = MaterialTheme.typography.displayLarge.copy(
                        color = Color(0xFF1B4332)
                    )
                )
            }

            if (started) {
                // VideoPlayer 🔁 en bucle
                VideoPlayer(
                    videoUrl = it.videoUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("Tipo: ${it.type.name.lowercase().replaceFirstChar { c -> c.uppercase() }}")
                Text("Descripción: ${it.description}")
                Text("Repeticiones: ${it.reps}")

                if (totalSets > 1) {
                    Text("Sets completados: ${completedSets + 1}/$totalSets")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    "⏱ Tiempo restante: ${remainingTime}s",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = Color(0xFF40916C)
                    )
                )

                Spacer(modifier = Modifier.height(28.dp))

                Button(
                    onClick = {
                        if (completedSets + 1 < totalSets) {
                            completedSets++
                            exerciseTimer = parseDuration(it.reps)
                            remainingTime = exerciseTimer
                            timerRunning = true
                        } else {
                            if (index < routine.exercises.lastIndex) {
                                index++
                                completedSets = 0
                                started = false
                            } else {
                                viewModel.saveProgress(programName, dayNumber)
                                navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("dayCompleted", true)
                                navController.popBackStack()
                            }
                        }
                    },
                    enabled = remainingTime == 0,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B4332)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(
                        text = when {
                            completedSets + 1 < totalSets -> "Completar set"
                            index == routine.exercises.lastIndex -> "Finalizar rutina"
                            else -> "Siguiente ejercicio"
                        },
                        color = Color.White
                    )
                }
            }
        }
    } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No se encontró la rutina.")
    }
}
