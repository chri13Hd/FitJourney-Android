// CountdownTimer.kt
package com.tfg.gymapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import androidx.compose.ui.Modifier

@Composable
fun CountdownTimer(durationInSeconds: Int, onFinish: () -> Unit) {
    var timeLeft by remember { mutableIntStateOf(durationInSeconds) }

    LaunchedEffect(key1 = timeLeft) {
        if (timeLeft > 0) {
            delay(1000)
            timeLeft -= 1
        } else {
            onFinish()
        }
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60

    Text(
        text = String.format("Tiempo restante: %02d:%02d", minutes, seconds),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(8.dp)
    )
}
