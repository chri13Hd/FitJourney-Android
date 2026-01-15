package com.tfg.gymapp.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tfg.gymapp.navigation.Screen

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)

@Composable
fun BottomBar(navController: NavController, currentRoute: String) {
    val greenPrimary = Color(0xFF1E5631)
    val items = listOf(
        BottomNavItem(Screen.Home.route, "Inicio", Icons.Default.Home),
        BottomNavItem(Screen.Explore.route, "Explorar", Icons.Default.FitnessCenter),
        BottomNavItem(Screen.Progress.route, "Progreso", Icons.Default.BarChart),
        BottomNavItem(Screen.Nutrition.route, "Nutricion", Icons.Default.Restaurant),
        BottomNavItem(Screen.Profile.route, "Perfil", Icons.Default.Person)
    )

    NavigationBar(
        containerColor = Color(0xFFF8F8F8),
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.route
            val animatedColor by animateColorAsState(
                if (selected) greenPrimary else Color.Gray,
                label = "navItemColor"
            )

            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (!selected) navController.navigate(item.route) {
                        popUpTo(Screen.Home.route) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = animatedColor
                    )
                },
                label = {
                    Text(
                        item.label,
                        color = animatedColor
                    )
                },
                alwaysShowLabel = true
            )
        }
    }
}
