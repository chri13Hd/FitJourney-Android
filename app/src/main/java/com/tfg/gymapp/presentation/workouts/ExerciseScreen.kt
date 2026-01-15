package com.tfg.gymapp.presentation.workouts

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tfg.gymapp.data.model.ExerciseInfo

@Composable
fun ExerciseScreen(
    navController: NavController,
    goal: String,
    viewModel: ExerciseViewModel,
    sharedViewModel: ExerciseSharedViewModel
) {
    val exercises by viewModel.exercises.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(goal) {
        viewModel.loadExercisesByGoal(goal)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color(0xFF1E5631))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Ejercicios: ${goal.replaceFirstChar { it.uppercase() }}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E5631)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Buscar ejercicios...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1E5631),
                focusedLabelColor = Color(0xFF1E5631),
                cursorColor = Color(0xFF1E5631)
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (exercises.isEmpty()) {
            CircularProgressIndicator(color = Color(0xFF1E5631))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Cargando ejercicios...", color = Color.Gray)
        } else {
            val filtered = exercises.filter { exercise ->
                val name = exercise.translations.find { it.language == 4 }?.name
                    ?: exercise.translations.find { it.language == 2 }?.name ?: ""
                val description = exercise.translations.find { it.language == 4 }?.description
                    ?: exercise.translations.find { it.language == 2 }?.description ?: ""
                val categoryId = exercise.category?.id

                (searchQuery.isBlank() || name.contains(searchQuery, true)) &&
                        when (goal.lowercase()) {
                            "club" -> categoryId in listOf(8, 10)
                            "home" -> categoryId in listOf(9, 11)
                            "yoga", "stretching" -> name.contains("yoga", true)
                                    || name.contains("stretch", true)
                                    || description.contains("yoga", true)
                                    || description.contains("stretch", true)
                            "programs" -> exercise.images.isNotEmpty()
                            else -> false
                        }
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(filtered) { exercise ->
                    val translation = exercise.translations.find { it.language == 4 }
                        ?: exercise.translations.find { it.language == 2 }
                    val name = translation?.name ?: "Nombre no disponible"
                    //val description = translation?.description ?: "Descripción no disponible"
                    val imageUrl = exercise.images.firstOrNull()?.image.orEmpty()

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                Log.d("ExerciseClick", "Ejercicio seleccionado: ${exercise.id} - ${exercise.name}")
                                navController.navigate("exercise_detail") {
                                    launchSingleTop = true
                                }
                                navController.getBackStackEntry("exercise_detail").savedStateHandle.set("exercise", exercise)
                            },
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                                Text(
                                    text = name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    color = Color(0xFF1E5631)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                /*Text(
                                    text = description.take(120) + "...",
                                    fontSize = 14.sp,
                                    color = Color.Gray,
                                    overflow = TextOverflow.Ellipsis
                                )*/
                            }

                            if (imageUrl.isNotBlank()) {
                                Image(
                                    painter = rememberAsyncImagePainter(imageUrl),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(90.dp)
                                        .aspectRatio(1f),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
