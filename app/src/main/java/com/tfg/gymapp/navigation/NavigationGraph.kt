package com.tfg.gymapp.navigation

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.tfg.gymapp.data.model.ExerciseInfo
import com.tfg.gymapp.presentation.auth.*
import com.tfg.gymapp.presentation.calculator.CaloricCalculatorScreen
import com.tfg.gymapp.presentation.components.BottomBar
import com.tfg.gymapp.presentation.explore.*
import com.tfg.gymapp.presentation.home.HomeScreen
import com.tfg.gymapp.presentation.nutrition.NutritionScreen
import com.tfg.gymapp.presentation.nutrition.RecipeDetailScreen
import com.tfg.gymapp.presentation.onboarding.*
import com.tfg.gymapp.presentation.profile.MyDetailsScreen
import com.tfg.gymapp.presentation.profile.ProfileScreen
import com.tfg.gymapp.presentation.profile.SettingsScreen
import com.tfg.gymapp.presentation.profile.UserProfileSetupScreen
import com.tfg.gymapp.data.program.DailyDetailScreen
import com.tfg.gymapp.data.program.ProgramContentScreen
import com.tfg.gymapp.presentation.nutrition.SportNutritionScreen
import com.tfg.gymapp.presentation.nutrition.SupplementDetailScreen
import com.tfg.gymapp.presentation.nutrition.SupplementSublistScreen
import com.tfg.gymapp.presentation.programs.ProgramDetailScreen
import com.tfg.gymapp.presentation.programs.ProgramScreen
import com.tfg.gymapp.presentation.progress.ProgressScreen
import com.tfg.gymapp.presentation.splash.SplashScreen
import com.tfg.gymapp.presentation.workouts.ExerciseDetailScreen
import com.tfg.gymapp.presentation.workouts.ExerciseScreen
import com.tfg.gymapp.presentation.workouts.ExerciseSharedViewModel
import com.tfg.gymapp.presentation.workouts.ExerciseViewModel
import com.tfg.gymapp.presentation.routine.DailyRoutineScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import com.tfg.gymapp.R

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Auth : Screen("auth")
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Profile : Screen("profile")
    object Login : Screen("login")
    object Register : Screen("register")
    object ForgotPassword : Screen("forgot_password")
    object Progress : Screen("progress")
    object Nutrition : Screen("nutrition")
    object Settings : Screen("settings")
    object CalorieCalculator : Screen("calorie_calculator")
    object Blog : Screen("blog")
    object Program : Screen("program")
    object SportsNutrition : Screen("sports_nutrition")
    object Exercises : Screen("exercises/{goal}") {
        fun createRoute(goal: String) = "exercises/$goal"
    }

    object RecipeDetail : Screen("recipeDetail/{recipeId}") {
        fun createRoute(recipeId: Int): String = "recipe_detail/$recipeId"
    }

    object UserProfileSetup : Screen("user_profile_setup/{uid}") {
        fun createRoute(uid: String) = "user_profile_setup/$uid"
    }

    object GoalSelection : Screen("goal_selection/{uid}") {
        fun createRoute(uid: String) = "goal_selection/$uid"
    }

    object MealPlan : Screen("meal_plan/{uid}") {
        fun createRoute(uid: String) = "meal_plan/$uid"
    }

    object Summary : Screen("summary/{uid}") {
        fun createRoute(uid: String) = "summary/$uid"
    }

    object MyDetail : Screen("my_detail/{uid}") {
        fun createRoute(uid: String) = "my_detail/$uid"
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationGraph(navController: NavHostController = rememberNavController()) {
    val routesWithBottomBar = listOf(
        Screen.Home.route,
        Screen.Explore.route,
        Screen.Progress.route,
        Screen.Nutrition.route,
        Screen.Profile.route
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in routesWithBottomBar) {
                BottomBar(navController = navController, currentRoute = currentRoute ?: "")
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) { SplashScreen(navController) }
            composable(Screen.Auth.route) { AuthScreen(navController) }
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Explore.route) { ExploreScreen(navController) }
            composable(Screen.Profile.route) { ProfileScreen(navController) }
            composable(Screen.Progress.route) { ProgressScreen(viewModel()) }
            composable(Screen.Nutrition.route) { NutritionScreen(navController) }
            composable(Screen.Login.route) { LoginScreen(navController) }
            composable(Screen.Register.route) { RegisterScreen(navController) }
            composable(Screen.ForgotPassword.route) { ForgotPasswordScreen(navController) }
            composable(Screen.Settings.route) { SettingsScreen(navController) }
            composable(Screen.CalorieCalculator.route) { CaloricCalculatorScreen(navController) }
            composable(Screen.Blog.route) { BlogScreen(navController) }

            composable(
                route = Screen.Exercises.route,
                arguments = listOf(navArgument("goal") { type = NavType.StringType })
            ) { backStackEntry ->
                val goal = backStackEntry.arguments?.getString("goal") ?: "fuerza"
                val exerciseViewModel: ExerciseViewModel = viewModel(backStackEntry)
                val sharedViewModel: ExerciseSharedViewModel = viewModel(backStackEntry)
                ExerciseScreen(
                    navController = navController,
                    goal = goal,
                    viewModel = exerciseViewModel,
                    sharedViewModel = sharedViewModel
                )
            }

            composable("exercise_detail") { backStackEntry ->
                val exercise = backStackEntry
                    .savedStateHandle
                    .get<ExerciseInfo>("exercise")

                Log.d("NavGraph", "Ejercicio recibido: $exercise")

                if (exercise != null) {
                    ExerciseDetailScreen(navController, exercise)
                } else {
                    Log.e("NavGraph", "❌ No se pudo recuperar el ejercicio desde savedStateHandle")
                    Text("No se pudo cargar el ejercicio", color = MaterialTheme.colorScheme.error)
                }

            }
                composable(Screen.Program.route) {
                ProgramScreen(navController)
            }

            composable(
                route = "programDetail/{programName}",
                arguments = listOf(navArgument("programName") { type = NavType.StringType })
            ) { backStackEntry ->
                val programName = backStackEntry.arguments?.getString("programName") ?: ""
                ProgramDetailScreen(programName = programName, navController = navController)
            }

            composable(
                "program_content/{programName}",
                arguments = listOf(navArgument("programName") { type = NavType.StringType })
            ) { backStackEntry ->
                val programName = backStackEntry.arguments?.getString("programName") ?: ""
                ProgramContentScreen(programName, navController)
            }

            // Nutrición deportiva
            composable("sports_nutrition") { SportNutritionScreen(navController) }

            composable("sublist/{supplementType}") { backStack ->
                val type = backStack.arguments?.getString("supplementType") ?: ""
                SupplementSublistScreen(navController, type)
            }

            composable("detail/{title}/{imageResId}/{description}") { backStackEntry ->
                val title = backStackEntry.arguments?.getString("title") ?: ""
                val imageResId = backStackEntry.arguments?.getString("imageResId")?.toIntOrNull() ?: R.drawable.ic_default_frasco
                val description = backStackEntry.arguments?.getString("description") ?: ""

                SupplementDetailScreen(title = title, imageResId = imageResId, description = description, navController = navController)
            }

            composable(
                "program_detail/{programName}",
                arguments = listOf(navArgument("programName") { type = NavType.StringType })
            ) { backStackEntry ->
                val programName = backStackEntry.arguments?.getString("programName") ?: ""
                ProgramDetailScreen(programName, navController)
            }

            composable(
                route = "program_day/{programName}/{dayNumber}",
                arguments = listOf(
                    navArgument("programName") { type = NavType.StringType },
                    navArgument("dayNumber") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val programName = backStackEntry.arguments?.getString("programName") ?: ""
                val dayNumber = backStackEntry.arguments?.getInt("dayNumber") ?: 1
                DailyRoutineScreen(programName = programName, dayNumber = dayNumber, navController)
            }

            composable(
                route = "recipe_detail/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                val id = it.arguments?.getInt("id") ?: 0
                RecipeDetailScreen(recipeId = id, navController = navController)
            }

            composable(
                "daily_detail/{programName}/{dayNumber}",
                arguments = listOf(
                    navArgument("programName") { type = NavType.StringType },
                    navArgument("dayNumber") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val encodedProgramName = backStackEntry.arguments?.getString("programName") ?: ""
                val programName = URLDecoder.decode(encodedProgramName, StandardCharsets.UTF_8.toString())
                val dayNumber = backStackEntry.arguments?.getInt("dayNumber") ?: 1

                if (programName == "Comer más saludable") {
                    DailyDetailScreen(programName, dayNumber, navController)
                } else {
                    DailyRoutineScreen(programName, dayNumber, navController)
                }
            }

            composable(
                route = "recipeDetail/{recipeId}",
                arguments = listOf(navArgument("recipeId") { type = NavType.IntType })
            ) {
                val recipeId = it.arguments?.getInt("recipeId") ?: 0
                RecipeDetailScreen(recipeId = recipeId, navController = navController)
            }

            composable(
                route = Screen.UserProfileSetup.route,
                arguments = listOf(navArgument("uid") { type = NavType.StringType })
            ) {
                val uid = it.arguments?.getString("uid") ?: return@composable
                UserProfileSetupScreen(navController, uid)
            }

            composable(
                route = Screen.GoalSelection.route,
                arguments = listOf(navArgument("uid") { type = NavType.StringType })
            ) {
                val uid = it.arguments?.getString("uid") ?: return@composable
                GoalSelectionScreen(navController, uid)
            }

            composable(
                route = Screen.MealPlan.route,
                arguments = listOf(navArgument("uid") { type = NavType.StringType })
            ) {
                val uid = it.arguments?.getString("uid") ?: return@composable
                MealPlanScreen(navController, uid)
            }

            composable(
                route = Screen.Summary.route,
                arguments = listOf(navArgument("uid") { type = NavType.StringType })
            ) {
                val uid = it.arguments?.getString("uid") ?: return@composable
                SummaryScreen(navController, uid)
            }

            composable(
                route = Screen.MyDetail.route,
                arguments = listOf(navArgument("uid") { type = NavType.StringType })
            ) {
                MyDetailsScreen(navController)
            }
        }
    }
}

