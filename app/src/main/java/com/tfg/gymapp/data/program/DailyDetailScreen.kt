package com.tfg.gymapp.data.program

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tfg.gymapp.data.model.programDailyContentMap
import com.tfg.gymapp.presentation.components.VideoPlayer
import com.tfg.gymapp.presentation.nutrition.NutritionViewModel
import com.tfg.gymapp.presentation.programs.ProgramProgressViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun DailyDetailScreen(
    programName: String,
    dayNumber: Int,
    navController: NavController
) {
    val context = LocalContext.current
    val activity = programDailyContentMap[programName]?.find { it.dayNumber == dayNumber }
    val programProgressViewModel: ProgramProgressViewModel = viewModel()
    val nutritionViewModel: NutritionViewModel = viewModel()

    val recipes = nutritionViewModel.recipes
    val suggestedRecipe = if (recipes.isNotEmpty()) {
        recipes[dayNumber % recipes.size]
    } else null

    activity?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9FDF9))
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Atrás",
                    tint = Color(0xFF2D6A4F)
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Día ${it.dayNumber}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B4332)
                )

                if (programName == "Comer más saludable") {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = it.title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF40916C)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = it.description,
                        fontSize = 16.sp,
                        color = Color(0xFF495057)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    VideoPlayer(
                        videoUrl = it.videoUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    // Aprende más mejorado
                    Text(
                        text = "Aprende más",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1B4332)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    val infoText = when (it.dayNumber) {
                        1 -> "La planificación semanal es una herramienta fundamental para llevar una alimentación equilibrada y consciente. Organizar tus comidas con anticipación no solo te permite ahorrar tiempo durante la semana, sino que también ayuda a reducir el desperdicio de alimentos. Al saber qué vas a comer cada día, es más fácil evitar tentaciones y tomar decisiones más saludables. Además, planificar te ayuda a mantener un mejor control de tus porciones y a incluir variedad de alimentos en tu dieta, asegurando así que tu cuerpo reciba los nutrientes que necesita."

                        2 -> "Una compra inteligente empieza mucho antes de entrar al supermercado. El primer paso es sentarte con calma a planear tus comidas de la semana, identificar lo que realmente necesitas y hacer una lista detallada. Esto no solo evita compras impulsivas, sino que también te permite comparar etiquetas nutricionales para elegir productos de mejor calidad. Recuerda que una buena compra es la base de una alimentación saludable. Al tener los ingredientes correctos en casa, es más fácil preparar comidas equilibradas y evitar recurrir a opciones poco nutritivas."

                        3 -> "Una comida equilibrada es aquella que incluye una combinación adecuada de macronutrientes: proteínas para la reparación y construcción muscular, carbohidratos como fuente principal de energía, grasas saludables que favorecen funciones cerebrales y hormonales, y fibra para una digestión eficiente. Al comer de esta forma, no solo mantienes estables tus niveles de energía, sino que también mejoras tu saciedad y bienestar general. Incorporar vegetales, granos enteros, legumbres, frutos secos y fuentes de proteína magra es una excelente manera de comenzar."

                        4 -> "Las tentaciones están por todas partes: en la oficina, en casa, en reuniones o incluso en momentos de ansiedad. Sin embargo, con una buena planificación y algunos snacks saludables siempre a mano, puedes evitar caer en decisiones impulsivas. Frutas, frutos secos, yogur natural o barritas caseras pueden ser grandes aliados. Recuerda que no se trata de prohibirse todo, sino de aprender a encontrar un equilibrio que funcione para ti. Estar preparado te da el control para tomar mejores decisiones incluso en momentos difíciles."

                        5 -> "Cocinar saludable no significa complicarse. Muchas veces, los platos más sencillos son también los más nutritivos y deliciosos. Usar ingredientes frescos, naturales y de temporada puede hacer una gran diferencia en sabor y calidad. Evita los ultraprocesados y apuesta por preparaciones básicas: salteados, ensaladas, asados al horno o cremas de verduras. Lo importante es mantenerlo simple, práctico y sabroso. La clave está en la constancia, no en la perfección."

                        6 -> "Mantener la cocina ordenada es más importante de lo que parece. Un espacio limpio y organizado te anima a cocinar más seguido, facilita la preparación de tus recetas y evita el desperdicio, ya que sabrás qué alimentos tienes disponibles. Guarda tus ingredientes por categorías, ten a mano lo que más usas y mantén tu nevera visible y clara. Un entorno agradable y funcional puede motivarte a mantener una rutina alimentaria saludable, y también te ahorra tiempo y frustraciones."

                        7 -> "Tener un día libre en tu alimentación también es parte de una vida saludable. No se trata de caer en excesos sin control, sino de permitirte disfrutar de tus comidas favoritas con consciencia y sin culpa. Este día puede ayudarte a mantener el equilibrio emocional y a seguir tu plan con más motivación. Recuerda: lo que hagas la mayor parte del tiempo es lo que marca la diferencia, no una comida puntual. Aprende a disfrutar sin castigarte, sabiendo que cuidarte también implica flexibilidad."

                        else -> "Recuerda que cada paso que das hacia una vida más saludable cuenta. No importa si los cambios son pequeños, lo importante es mantener una actitud constante y positiva. Cada elección consciente que haces te acerca a tus metas. No se trata de ser perfecto, sino de mejorar cada día, aprendiendo de los errores y celebrando los logros. Estás construyendo un estilo de vida sostenible, uno que funcione para ti y que te haga sentir bien contigo mismo."
                    }

                    Text(
                        text = infoText,
                        fontSize = 15.sp,
                        color = Color(0xFF495057),
                        lineHeight = 20.sp
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Contenedor del plato sugerido
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F8F4)),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Plato sugerido del día",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF2D6A4F)
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            if (suggestedRecipe != null) {
                                Text(
                                    text = suggestedRecipe.title,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF1B4332)
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Este plato está pensado para complementar tu alimentación saludable de hoy.",
                                    fontSize = 14.sp,
                                    color = Color(0xFF495057)
                                )

                                Spacer(modifier = Modifier.height(10.dp))

                                Text(
                                    text = "Pulsa para ver la receta completa",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = Color(0xFF0077B6),
                                    textDecoration = TextDecoration.Underline,
                                    modifier = Modifier.clickable {
                                        try {
                                            navController.navigate("recipeDetail/${suggestedRecipe.id}")
                                        } catch (_: Exception) {
                                            Toast.makeText(context, "No se pudo abrir la receta.", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                )
                            } else {
                                Text("No se encontró un plato sugerido.")
                            }
                        }
                    }
                } else {
                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = it.description,
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    VideoPlayer(
                        videoUrl = it.videoUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }

            Button(
                onClick = {
                    programProgressViewModel.saveProgress(programName, dayNumber)
                    // Reemplaza el popBackStack() actual por:
                    val encoded = URLEncoder.encode(programName, StandardCharsets.UTF_8.toString())
                    val destination = "programDetail/$encoded?timestamp=${System.currentTimeMillis()}"
                    navController.navigate(destination) {
                        popUpTo("programDetail/$encoded") {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }

                    Toast.makeText(
                        context,
                        "¡Día completado! El siguiente ya está disponible.",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.popBackStack()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2D6A4F))
            ) {
                Text("Finalizar día", color = Color.White)
            }
        }
    } ?: Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("No se encontró contenido para este día.")
    }
}
