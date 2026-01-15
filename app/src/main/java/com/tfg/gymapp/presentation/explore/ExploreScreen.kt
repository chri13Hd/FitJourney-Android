package com.tfg.gymapp.presentation.explore

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tfg.gymapp.R
import com.tfg.gymapp.navigation.Screen
import com.tfg.gymapp.ui.theme.GreenDark

data class ExploreItem(
    val title: String,
    val subtitle: String,
    val imageRes: Int,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(navController: NavController) {
    val items = listOf(
        ExploreItem("Ejercicios en el Gimnasio", "Entrena con máquinas y pesas", R.drawable.club_workout, Screen.Exercises.createRoute("club")),
        ExploreItem("Ejercicios en casa", "Entrenamientos sin salir de casa", R.drawable.home_workout, Screen.Exercises.createRoute("home")),
        ExploreItem("Programas", "Planes por objetivos y semanas", R.drawable.programs, Screen.Program.route),
        ExploreItem("Nutrición Deportiva", "Recetas y consejos por dieta", R.drawable.nutrition, Screen.SportsNutrition.route),
        ExploreItem("Ciencia & Blog", "Estudios y artículos sobre entrenamiento", R.drawable.blog, Screen.Blog.route),
        ExploreItem("Calculadora de calorías", "Calcula tus calorías ideales", R.drawable.calculator, Screen.CalorieCalculator.route)
    )

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Explorar", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = GreenDark,
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFFDFCF8)
    ) { paddingValues ->
        AnimatedVisibility(visible = visible, enter = fadeIn(), exit = fadeOut()) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .background(Color(0xFFFDFCF8)),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
            ) {
                items(items) { item ->
                    ExploreCard(item = item) {
                        navController.navigate(item.route)
                    }
                }
            }
        }
    }
}

@Composable
fun ExploreCard(item: ExploreItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp)
    ) {
        Box {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f))
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = item.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = item.subtitle,
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}
