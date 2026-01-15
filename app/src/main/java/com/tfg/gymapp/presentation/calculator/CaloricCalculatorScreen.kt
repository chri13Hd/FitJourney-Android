package com.tfg.gymapp.presentation.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

fun calculateCalories(
    age: Int,
    weight: Float,
    height: Float,
    gender: String,
    activityLevel: Float
): Int {
    val bmr = if (gender == "male") {
        10 * weight + 6.25 * height - 5 * age + 5
    } else {
        10 * weight + 6.25 * height - 5 * age - 161
    }
    return (bmr * activityLevel).toInt()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaloricCalculatorScreen(navController: NavController) {
    var age by remember { mutableIntStateOf(0) }
    var weight by remember { mutableFloatStateOf(0f) }
    var height by remember { mutableFloatStateOf(0f) }
    var gender by remember { mutableStateOf("male") }
    var activityLevel by remember { mutableFloatStateOf(1.2f) }
    var result by remember { mutableIntStateOf(0) }

    var ageError by remember { mutableStateOf(false) }
    var weightError by remember { mutableStateOf(false) }
    var heightError by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFFDFCF8))
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color(0xFF1E5631)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Calculadora de Calorías",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E5631)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Edad
        OutlinedTextField(
            value = if (age == 0) "" else age.toString(),
            onValueChange = {
                age = it.toIntOrNull() ?: 0
                ageError = false
            },
            label = { Text("Edad") },
            isError = ageError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
        )
        if (ageError) Text("Este campo es obligatorio", color = Color.Red, fontSize = 12.sp)

        // Peso
        OutlinedTextField(
            value = if (weight == 0f) "" else weight.toString(),
            onValueChange = {
                weight = it.toFloatOrNull() ?: 0f
                weightError = false
            },
            label = { Text("Peso (kg)") },
            isError = weightError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
        )
        if (weightError) Text("Este campo es obligatorio", color = Color.Red, fontSize = 12.sp)

        // Altura
        OutlinedTextField(
            value = if (height == 0f) "" else height.toString(),
            onValueChange = {
                height = it.toFloatOrNull() ?: 0f
                heightError = false
            },
            label = { Text("Altura (cm)") },
            isError = heightError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
        )
        if (heightError) Text("Este campo es obligatorio", color = Color.Red, fontSize = 12.sp)

        // Género
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            TextButton(onClick = { gender = "male" }, colors = ButtonDefaults.textButtonColors(contentColor = if (gender == "male") Color(0xFF1E5631) else Color.Gray)) {
                Text("Hombre")
            }
            TextButton(onClick = { gender = "female" }, colors = ButtonDefaults.textButtonColors(contentColor = if (gender == "female") Color(0xFF1E5631) else Color.Gray)) {
                Text("Mujer")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nivel de actividad
        Text("Nivel de actividad", fontSize = 16.sp)
        DropdownMenuWithValues(selectedValue = activityLevel) { selected -> activityLevel = selected }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                ageError = age == 0
                weightError = weight == 0f
                heightError = height == 0f

                if (!ageError && !weightError && !heightError) {
                    result = calculateCalories(age, weight, height, gender, activityLevel)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E5631))
        ) {
            Text("Calcular", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (result > 0) {
            Text("Calorías estimadas: $result kcal/día", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Esta calculadora utiliza la fórmula de Mifflin-St Jeor para estimar tu tasa metabólica basal (TMB) y la multiplica por tu nivel de actividad.\n\n➡️ Para volumen: suma 250-500 kcal.\n➡️ Para definición: resta 250-500 kcal.\n\nEstos valores son aproximados y deben ajustarse a tu evolución.",
                fontSize = 14.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
fun DropdownMenuWithValues(
    selectedValue: Float,
    onValueSelected: (Float) -> Unit
) {
    val activityLevels = listOf(
        1.2f to "Sedentario (poca o ninguna actividad)",
        1.375f to "Actividad ligera (1-3 días/semana)",
        1.55f to "Actividad moderada (3-5 días/semana)",
        1.725f to "Muy activo (6-7 días/semana)",
        1.9f to "Extremadamente activo (2 veces al día)"
    )

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray, RoundedCornerShape(10.dp))
                .clickable { expanded = true }
                .background(Color.White)
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(
                text = activityLevels.find { it.first == selectedValue }?.second
                    ?: "Selecciona tu nivel de actividad",
                fontSize = 15.sp,
                color = Color.DarkGray
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            activityLevels.forEach { (value, label) ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = label,
                            fontSize = 14.sp,
                            lineHeight = 18.sp
                        )
                    },
                    onClick = {
                        onValueSelected(value)
                        expanded = false
                    },
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}


