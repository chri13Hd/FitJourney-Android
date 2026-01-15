package com.tfg.gymapp.presentation.nutrition

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BakeryDining
import androidx.compose.material.icons.filled.EmojiFoodBeverage
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tfg.gymapp.data.api.RetrofitClient
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(navController: NavController, recipeId: Int) {
    val scope = rememberCoroutineScope()
    var recipeDetail by remember { mutableStateOf<RecipeDetailResponse?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    val nutritionHtmlUrl =
        "https://api.spoonacular.com/recipes/$recipeId/nutritionLabel?apiKey=7053a28dc4a1444b9d0dc58943b864dd"

    LaunchedEffect(recipeId) {
        scope.launch {
            try {
                val response = RetrofitClient.api.getRecipeInformation(
                    id = recipeId,
                    apiKey = "7053a28dc4a1444b9d0dc58943b864dd"
                )
                recipeDetail = response
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de la receta", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E5631),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            recipeDetail?.let { recipe ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = recipe.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(recipe.title, fontWeight = FontWeight.Bold, fontSize = 22.sp)

                    Divider(modifier = Modifier.padding(vertical = 16.dp))

                    val nutrients = recipe.nutrition?.nutrients
                    fun findNutrient(name: String): Nutrient? {
                        return nutrients?.firstOrNull {
                            it.name.contains(name, ignoreCase = true)
                        }
                    }

                    val calories = findNutrient("calories")
                    val protein = findNutrient("protein")
                    val carbs = findNutrient("carbohydrate")
                    val fat = findNutrient("fat")

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconWithLabel(Icons.Filled.LocalFireDepartment, "${calories?.amount?.toInt() ?: "--"} kcal")
                        IconWithLabel(Icons.Filled.FitnessCenter, "${protein?.amount?.toInt() ?: "--"}g")
                        IconWithLabel(Icons.Filled.BakeryDining, "${carbs?.amount?.toInt() ?: "--"}g")
                        IconWithLabel(Icons.Filled.EmojiFoodBeverage, "${fat?.amount?.toInt() ?: "--"}g")
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text("Preparación del plato", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    val instructionsParsed = recipe.instructions?.let {
                        HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
                    } ?: ""

                    if (instructionsParsed.isBlank()) {
                        Text("No se proporcionaron instrucciones.", fontSize = 15.sp)
                    } else {
                        instructionsParsed.split("\n")
                            .filter { it.isNotBlank() }
                            .forEachIndexed { index, step ->
                                Text("${index + 1}. $step", fontSize = 15.sp, modifier = Modifier.padding(bottom = 6.dp))
                            }
                    }

                    Divider(modifier = Modifier.padding(vertical = 16.dp))

                    Text("Ingredientes", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    recipe.extendedIngredients.forEach {
                        Text("• ${it.amount} ${it.unit} ${it.name}", fontSize = 15.sp)
                    }

                    Divider(modifier = Modifier.padding(vertical = 16.dp))

                    Text("Etiqueta nutricional completa", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(8.dp))

                    AndroidView(
                        factory = { context ->
                            WebView(context).apply {
                                webViewClient = WebViewClient()
                                settings.javaScriptEnabled = true
                                loadUrl(nutritionHtmlUrl)
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                    )

                    Spacer(modifier = Modifier.height(32.dp))
                }
            } ?: run {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No se pudo cargar la receta.")
                }
            }
        }
    }
}

@Composable
fun IconWithLabel(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, contentDescription = null)
        Text(label, fontSize = 13.sp)
    }
}
