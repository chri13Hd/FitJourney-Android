package com.tfg.gymapp.presentation.home

import android.Manifest
import android.location.Geocoder
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.permissions.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.tfg.gymapp.R
import com.tfg.gymapp.data.utils.fetchWeather
import kotlinx.coroutines.tasks.await
import java.util.*
import com.tfg.gymapp.data.model.programRoutineMap

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    val fusedLocationClient = remember {
        com.google.android.gms.location.LocationServices.getFusedLocationProviderClient(context)
    }

    val userName = remember { mutableStateOf("") }
    val sexo = remember { mutableStateOf("Hombre") }
    val city = remember { mutableStateOf("Madrid") }
    val weather = remember { mutableStateOf("") }
    val temperature = remember { mutableStateOf("") }
    val motivationalMessage = remember { mutableStateOf("") }
    val userGoals = remember { mutableStateOf<List<String>>(emptyList()) }
    val selectedGoal = remember { mutableStateOf("") }
    val hasStartedGoal = remember { mutableStateOf(false) }

    val greenPrimary = Color(0xFF1E5631)
    val backgroundColor = Color(0xFFFAF9F0)
    val waterIntake = remember { mutableIntStateOf(0) }
    val uid = FirebaseAuth.getInstance().currentUser?.uid
    var animateScale by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (animateScale) 1.3f else 1f, animationSpec = tween(300), label = "scale")
    val dateFormat = remember { java.text.SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()) }

    val recommendedPrograms = listOf(
        "Bajar de peso" to "Pérdida de peso",
        "Ganar masa muscular" to "Ganar masa muscular",
        "Flexibilidad" to "Flexibilidad",
        "Mejorar resistencia" to "Mejorar resistencia",
        "Comer más saludable" to "Comer más saludable",
        "Tonificar cuerpo" to "Tonificar cuerpo"
    )


    // ✅ Carga datos de usuario y verifica progreso del objetivo principal
    LaunchedEffect(uid) {
        uid?.let { userId ->
            val userDoc = FirebaseFirestore.getInstance().collection("Users").document(userId).get().await()
            userName.value = userDoc.getString("name") ?: "Usuario"
            sexo.value = userDoc.getString("gender") ?: "Hombre"
            userGoals.value = userDoc.get("goals") as? List<String> ?: emptyList()
            selectedGoal.value = userDoc.getString("selectedGoal") ?: userGoals.value.firstOrNull() ?: ""

            val progressDoc = FirebaseFirestore.getInstance().collection("UserProgress").document(userId).get().await()
            val completedDays = progressDoc.get("completedDays") as? Map<*, *> ?: emptyMap<String, Any>()
            hasStartedGoal.value = completedDays.containsKey(selectedGoal.value)

            val savedDate = progressDoc.getString("lastWaterDate") ?: ""
            val today = dateFormat.format(Date())

            if (savedDate != today) {
                FirebaseFirestore.getInstance().collection("UserProgress").document(userId).set(
                    mapOf("waterIntake" to 0, "lastWaterDate" to today),
                    SetOptions.merge()
                ).await()
                waterIntake.intValue = 0
            } else {
                waterIntake.intValue = (progressDoc.getLong("waterIntake") ?: 0).toInt()
            }
        }
    }

    // ✅ Solicita ubicación
    LaunchedEffect(Unit) { locationPermissionState.launchPermissionRequest() }

    // ✅ Detecta ciudad
    LaunchedEffect(locationPermissionState.status) {
        if (locationPermissionState.status is PermissionStatus.Granted) {
            try {
                val result = fusedLocationClient.lastLocation.await()
                result?.let {
                    val geoCoder = Geocoder(context, Locale.getDefault())
                    val address = geoCoder.getFromLocation(it.latitude, it.longitude, 1)?.firstOrNull()
                    city.value = address?.locality ?: "Ubicación desconocida"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // ✅ Clima
    LaunchedEffect(city.value) {
        fetchWeather(city = city.value, weather = weather, temperature = temperature, motivationalMessage = motivationalMessage, userGoals = userGoals.value)
    }

    val imagenResId = if (sexo.value == "Femenino") R.drawable.imagen_motivacional else R.drawable.imagen_motivacional_hombre

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    animateScale = true
                    if (waterIntake.intValue < 8) {
                        val newValue = waterIntake.intValue + 1
                        uid?.let {
                            val userRef = FirebaseFirestore.getInstance().collection("UserProgress").document(it)
                            userRef.set(
                                mapOf(
                                    "waterIntake" to newValue,
                                    "lastWaterDate" to dateFormat.format(Date())
                                ),
                                SetOptions.merge()
                            ).addOnSuccessListener {
                                waterIntake.intValue = newValue
                                Toast.makeText(context, "Has tomado un vaso de agua", Toast.LENGTH_SHORT).show()
                            }.addOnFailureListener {
                                userRef.set(mapOf("waterIntake" to newValue))
                                waterIntake.intValue = newValue
                                Toast.makeText(context, "Has tomado un vaso de agua", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(context, "¡Has alcanzado tu objetivo de hidratación!", Toast.LENGTH_SHORT).show()
                    }
                    animateScale = false
                },
                containerColor = greenPrimary,
                modifier = Modifier
                    .padding(16.dp)
                    .graphicsLayer(scaleX = scale, scaleY = scale)
            ) {
                Icon(Icons.Default.LocalDrink, contentDescription = "Añadir vaso de agua", tint = Color.White)
            }
        },
        containerColor = backgroundColor
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .background(backgroundColor)
        ) {
            // Cabecera
            Box(modifier = Modifier.fillMaxWidth().height(420.dp)) {
                Image(
                    painter = painterResource(id = imagenResId),
                    contentDescription = "Imagen motivacional",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, backgroundColor),
                                startY = 90f, endY = 1180f
                            )
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Fit Journey", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)

                    if (weather.value.isNotEmpty() && temperature.value != "--") {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val icon = when {
                                weather.value.contains("lluvia", true) -> "🌧️"
                                weather.value.contains("nubes", true) -> "☁️"
                                weather.value.contains("sol", true) || weather.value.contains("despejado", true) -> "☀️"
                                else -> "🌤️"
                            }
                            Text("$icon ${temperature.value}°C", color = Color.Black, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                            Text(city.value, color = Color.DarkGray, fontSize = 12.sp)
                            Text(weather.value, color = Color.DarkGray, fontSize = 12.sp)
                        }
                    }
                }

                Column(
                    modifier = Modifier.align(Alignment.BottomStart).padding(horizontal = 16.dp, vertical = 24.dp)
                ) {
                    Text("HOLA", color = greenPrimary, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    Text(userName.value.uppercase(), color = greenPrimary, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Cuerpo
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
                Text(motivationalMessage.value, fontSize = 15.sp, color = greenPrimary, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(16.dp))

                Text("¡Bienvenido a tu viaje de progreso!", color = Color.DarkGray, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))

                val programName = selectedGoal.value

                Button(
                    onClick = {
                        if (programName.isNotBlank()) {
                            navController.navigate("program_detail/${programName}")
                        } else {
                            Toast.makeText(context, "Selecciona un objetivo primero", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = greenPrimary)
                ) {
                    Text(
                        if (hasStartedGoal.value) "Continuar donde lo dejaste" else "Comenzar entrenamiento",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Spacer(modifier = Modifier.height(16.dp))
                Text("Tu objetivo principal", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = greenPrimary)
                Spacer(modifier = Modifier.height(8.dp))

                val mainGoal = selectedGoal.value
                val daysCompleted = remember { mutableIntStateOf(0) }

                LaunchedEffect(mainGoal) {
                    uid?.let { userId ->
                        val progressSnapshot = FirebaseFirestore.getInstance().collection("UserProgress").document(userId).get().await()
                        val completedMap = progressSnapshot.get("completedDays") as? Map<*, *>
                        val days = completedMap?.get(mainGoal) as? List<*>
                        daysCompleted.intValue = days?.size ?: 0
                    }
                }

                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Objetivo: $mainGoal", fontWeight = FontWeight.Bold, color = greenPrimary)
                        Spacer(modifier = Modifier.height(4.dp))
                        val totalDiasMainGoal = programRoutineMap[mainGoal]?.size ?: 0
                        Text("Días completados: ${daysCompleted.intValue} de $totalDiasMainGoal", color = Color.DarkGray)
                    }
                }

                Text("Recomendados para ti", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = greenPrimary)
                Spacer(modifier = Modifier.height(12.dp))

                val programTitles = listOf(
                    Pair("Bajar de peso", R.drawable.perdida_peso),
                    Pair("Ganar masa muscular", R.drawable.ganar_musculo),
                    Pair("Flexibilidad", R.drawable.flexibilidad),
                    Pair("Mejorar resistencia", R.drawable.mejorar_resistencia),
                    Pair("Comer más saludable", R.drawable.comer_saludable),
                    Pair("Tonificar cuerpo", R.drawable.tonificar_cuerpo)
                )

                val filteredProgramTitles = programTitles.filter { it.first != selectedGoal.value }

                LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(filteredProgramTitles.size) { index ->
                        val program = filteredProgramTitles[index].first
                        val imageRes = filteredProgramTitles[index].second

                        val totalDias = if (program == "Comer más saludable") {
                            7
                        } else {
                            programRoutineMap[program]?.size ?: 0
                        }

                        Card(
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            modifier = Modifier
                                .width(220.dp)
                                .height(220.dp)
                        ) {
                            Column(modifier = Modifier.fillMaxSize()) {
                                Image(
                                    painter = painterResource(id = imageRes),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                                )

                                Column(
                                    modifier = Modifier
                                        .padding(12.dp)
                                        .fillMaxSize(),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(program, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = greenPrimary)
                                    Text("Días: $totalDias", fontSize = 14.sp, color = Color.DarkGray)
                                    Button(
                                        onClick = {
                                            navController.navigate("program_detail/$program")
                                        },
                                        modifier = Modifier.align(Alignment.End),
                                        colors = ButtonDefaults.buttonColors(containerColor = greenPrimary)
                                    ) {
                                        Text("Ver", color = Color.White, fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}
