package com.tfg.gymapp.presentation.progress

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.InsertChart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tfg.gymapp.R
import com.tfg.gymapp.ui.theme.GreenDark

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProgressScreen(viewModel: ProgressViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi progreso", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GreenDark,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFFAF9F0)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 32.dp)
                )
            } else {
                val mainGoal = state.fallbackGoal

                Text(
                    text = "Tu objetivo principal:",
                    style = MaterialTheme.typography.titleMedium.copy(color = GreenDark)
                )

                if (!mainGoal.isNullOrBlank()) {
                    CircularGoalProgressItem(
                        title = mainGoal,
                        percentage = state.percentageByProgram[mainGoal] ?: 0.0
                    )
                } else {
                    Text("⚠️ Aún no has seleccionado un objetivo principal", color = Color.Red)
                }

                val additional = state.percentageByProgram.filterKeys {
                    it != mainGoal && it.isNotBlank()
                }

                if (additional.isNotEmpty()) {
                    Text(
                        text = "Programas adicionales que has comenzado:",
                        style = MaterialTheme.typography.titleMedium.copy(color = GreenDark),
                        modifier = Modifier.padding(top = 24.dp)
                    )

                    additional.forEach { (title, percent) ->
                        CircularGoalProgressItem(title, percent)
                    }
                }

                state.userWeight?.let {
                    Text(
                        text = "Peso actual: ${it} kg",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }

                state.caloriesBurned?.let {
                    Text(
                        text = "Calorías quemadas: ${it.toInt()} kcal",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Hidratación diaria:",
                    style = MaterialTheme.typography.titleMedium.copy(color = GreenDark)
                )

                WaterProgressIndicator(current = state.waterIntake, goal = 8)
            }
        }
    }
}

@Composable
fun CircularGoalProgressItem(title: String, percentage: Double) {
    val animatedProgress by animateFloatAsState(
        targetValue = (percentage / 100f).toFloat(),
        animationSpec = tween(durationMillis = 600),
        label = ""
    )

    // Cambiar el color dinámicamente
    val progressColor = when {
        percentage < 25 -> Color(0xFFe63946)
        percentage < 50 -> Color(0xFFf4a261)
        percentage < 75 -> Color(0xFFf1c40f)
        else -> GreenDark
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    progress = animatedProgress,
                    strokeWidth = 6.dp,
                    color = progressColor,
                    trackColor = Color(0xFFE0E0E0),
                    strokeCap = StrokeCap.Round,
                    modifier = Modifier.fillMaxSize()
                )
                Text(
                    text = "${"%.0f".format(percentage)}%",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(color = GreenDark),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun WaterProgressIndicator(current: Int, goal: Int) {
    val filled = current.coerceAtMost(goal)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(goal) { index ->
            Icon(
                painter = painterResource(id = R.drawable.ic_water_glass),
                contentDescription = null,
                tint = if (index < filled) Color(0xFF74C69D) else Color(0xFFE0E0E0),
                modifier = Modifier.size(32.dp)
            )
        }
    }
    Text(
        text = "$filled / $goal vasos de agua",
        fontSize = 14.sp,
        color = Color.Gray,
        modifier = Modifier.padding(top = 4.dp)
    )
}
