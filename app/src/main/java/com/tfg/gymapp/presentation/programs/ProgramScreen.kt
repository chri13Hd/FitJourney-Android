package com.tfg.gymapp.presentation.programs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tfg.gymapp.R

data class Program(
    val name: String,
    val description: String,
    val weeks: Int,
    val imageRes: Int
)

@Composable
fun ProgramScreen(navController: NavController) {
    val programs = listOf(
        Program("Bajar de peso", "Quema grasa y mejora resistencia", 4, R.drawable.perdida_peso),
        Program("Ganar masa muscular", "Hipertrofia con sobrecarga progresiva", 6, R.drawable.ganar_musculo),
        Program("Flexibilidad", "Yoga y movilidad", 3, R.drawable.flexibilidad),
        Program("Mejorar resistencia", "Cardio y rutinas HIIT para aumentar la capacidad aeróbica", 5, R.drawable.mejorar_resistencia),
        Program("Comer más saludable", "Plan nutricional con enfoque en alimentos reales y balanceados", 4, R.drawable.comer_saludable),
        Program("Tonificar cuerpo", "Rutinas con peso corporal y bandas para definición muscular", 4, R.drawable.tonificar_cuerpo)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6FDF6))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color(0xFF1B4332)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Planes por objetivos",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B4332)
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(programs) { program ->
                ProgramCard(program = program) {
                    navController.navigate("programDetail/${program.name}")
                }
            }
        }
    }
}

@Composable
fun ProgramCard(program: Program, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE9F5EC)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = program.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = Color(0xFF2D6A4F)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Duración: ${program.weeks} semanas",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF4C6652)
                )
                Text(
                    text = "Descripción: ${program.description}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF344E41)
                )
            }

            Image(
                painter = painterResource(id = program.imageRes),
                contentDescription = program.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            )
        }
    }
}
