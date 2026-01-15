package com.tfg.gymapp.presentation.profile

import android.app.DatePickerDialog
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserProfileSetupScreen(navController: NavController, uid: String) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val greenPrimary = Color(0xFF1E5631)
    val backgroundColor = Color(0xFFFAF9F0)

    val animOffset = remember { Animatable(-300f) }
    LaunchedEffect(Unit) {
        animOffset.animateTo(0f, animationSpec = tween(600))
    }

    var gender by remember { mutableStateOf("") }
    val genderOptions = listOf("Masculino", "Femenino", "Otro")
    var expandedGender by remember { mutableStateOf(false) }

    var weight by remember { mutableStateOf("") }
    val weightOptions = (40..150 step 5).map { "$it kg" }
    var expandedWeight by remember { mutableStateOf(false) }

    var height by remember { mutableStateOf("") }
    val heightOptions = (140..210 step 1).map { "$it cm" }
    var expandedHeight by remember { mutableStateOf(false) }

    var targetWeight by remember { mutableStateOf("") }
    val targetWeightOptions = (40..150 step 5).map { "$it kg" }
    var expandedTargetWeight by remember { mutableStateOf(false) }

    var birthDate by remember { mutableStateOf("Selecciona una fecha") }
    var age by remember { mutableStateOf<Int?>(null) }

    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Perfil", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = greenPrimary)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .fillMaxSize()
                .offset(y = animOffset.value.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Completa tu perfil para personalizar tu experiencia:", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = greenPrimary)

            DropdownField("Sexo", gender, genderOptions, expandedGender, { expandedGender = it }, { gender = it })
            DropdownField("Peso (kg)", weight, weightOptions, expandedWeight, { expandedWeight = it }, { weight = it })
            DropdownField("Altura (cm)", height, heightOptions, expandedHeight, { expandedHeight = it }, { height = it })
            DropdownField("Peso objetivo", targetWeight, targetWeightOptions, expandedTargetWeight, { expandedTargetWeight = it }, { targetWeight = it })

            DatePickerField("Fecha de nacimiento", birthDate, onDateSelected = { selected, userAge ->
                birthDate = selected
                age = userAge
            })

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    if (gender.isBlank() || weight.isBlank() || height.isBlank() || targetWeight.isBlank() || birthDate == "Selecciona una fecha") {
                        scope.launch {
                            snackbarHostState.showSnackbar("Por favor, completa todos los campos.")
                        }
                        return@Button
                    }

                    if (age != null && age!! < 13) {
                        scope.launch {
                            snackbarHostState.showSnackbar("Debes tener al menos 13 años para registrarte.")
                        }
                        return@Button
                    }

                    isLoading = true

                    val data = mapOf(
                        "gender" to gender,
                        "birthDate" to birthDate,
                        "weight" to weight,
                        "height" to height,
                        "targetWeight" to targetWeight
                    )

                    FirebaseFirestore.getInstance().collection("Users")
                        .document(uid)
                        .update(data)
                        .addOnSuccessListener {
                            isLoading = false
                            navController.navigate("goal_selection/$uid")
                        }
                        .addOnFailureListener {
                            isLoading = false
                            scope.launch {
                                snackbarHostState.showSnackbar("Error al guardar: ${it.message}")
                            }
                        }
                },
                enabled = !isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = greenPrimary)
            ) {
                if (isLoading) CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                else Text("Continuar", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DatePickerField(label: String, selectedDate: String, onDateSelected: (String, Int) -> Unit) {
    val context = LocalContext.current

    Column {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .clickable {
                    val calendar = Calendar.getInstance()
                    val yearNow = calendar.get(Calendar.YEAR)
                    val monthNow = calendar.get(Calendar.MONTH)
                    val dayNow = calendar.get(Calendar.DAY_OF_MONTH)

                    DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            val formatted = "%02d/%02d/%d".format(dayOfMonth, month + 1, year)
                            val userAge = yearNow - year - if (monthNow < month || (monthNow == month && dayNow < dayOfMonth)) 1 else 0
                            onDateSelected(formatted, userAge)
                        },
                        yearNow,
                        monthNow,
                        dayNow
                    ).show()
                }
                .padding(16.dp)
        ) {
            Text(
                text = selectedDate,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(
    label: String,
    selectedValue: String,
    options: List<String>,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onSelect: (String) -> Unit
) {
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = onExpandedChange) {
        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            label = { Text(label) },
            readOnly = true,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { onExpandedChange(false) }) {
            options.forEach {
                DropdownMenuItem(text = { Text(it) }, onClick = {
                    onSelect(it)
                    onExpandedChange(false)
                })
            }
        }
    }
}
