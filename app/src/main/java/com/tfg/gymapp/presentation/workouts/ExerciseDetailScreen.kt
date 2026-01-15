package com.tfg.gymapp.presentation.workouts

import android.text.Html
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tfg.gymapp.data.model.ExerciseInfo
import kotlinx.coroutines.tasks.await

fun String.stripHtml(): String {
    return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
}

@Composable
fun VideoPlayer(modifier: Modifier = Modifier, videoUrl: String) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUrl.toUri()))
            prepare()
            playWhenReady = true
            repeatMode = ExoPlayer.REPEAT_MODE_ALL
        }
    }

    DisposableEffect(Unit) {
        onDispose { exoPlayer.release() }
    }

    AndroidView(
        factory = {
            PlayerView(context).apply {
                player = exoPlayer
                useController = true
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseDetailScreen(navController: NavController, exercise: ExerciseInfo) {

    var videoUrl by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(exercise.id) {
        try {
            Log.d("Firestore", "Video URL encontrada: $videoUrl")

            val snapshot = Firebase.firestore.collection("exerciseMedia")
                .whereEqualTo("exerciseId", exercise.id)
                .get()
                .await()
            videoUrl = snapshot.documents.firstOrNull()?.getString("videoUrl")
            Log.d("ExerciseDetail", "Ejercicio cargado: ${exercise.id} - ${exercise.name}")
        } catch (e: Exception) {
            Log.e("Firestore", "❌ Error al obtener video de Firestore: ${e.localizedMessage}")
            videoUrl = null
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle del Ejercicio") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            videoUrl?.let {
                VideoPlayer(
                    videoUrl = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                        .padding(bottom = 16.dp)
                )
            }

            val translation = exercise.translations.find { it.language == 4 }
                ?: exercise.translations.find { it.language == 2 }

            translation?.name?.let {
                Text(it, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(
                    "ID del ejercicio: ${exercise.id}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            translation?.description?.let { rawDescription ->
                val steps = rawDescription.stripHtml().split("\n").filter { it.isNotBlank() }

                Text("Instrucciones:", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                steps.forEachIndexed { index, step ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Row(modifier = Modifier.padding(12.dp)) {
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(28.dp)
                                    .align(Alignment.Top)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        "${index + 1}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(step, style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Información adicional", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    if (!exercise.muscles.isNullOrEmpty()) {
                        Text("Músculos principales: ${exercise.muscles.joinToString { it.name }}")
                    }
                    if (!exercise.muscles_secondary.isNullOrEmpty()) {
                        Text("Músculos secundarios: ${exercise.muscles_secondary.joinToString { it.name }}")
                    }
                    if (exercise.equipment.isNotEmpty()) {
                        Text("Equipamiento requerido: ${exercise.equipment.joinToString { it.name ?: "Desconocido" }}")
                    }
                    Text("Categoría: ${exercise.category?.name ?: "No especificada"}")
                }
            }
        }
    }
}
