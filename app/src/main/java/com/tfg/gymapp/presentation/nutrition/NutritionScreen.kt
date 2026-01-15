package com.tfg.gymapp.presentation.nutrition

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tfg.gymapp.data.api.RecipeItem
import com.tfg.gymapp.data.api.RetrofitClient
import com.tfg.gymapp.navigation.Screen
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutritionScreen(navController: NavController) {
    val greenPrimary = Color(0xFF1E5631)
    val recipes = remember { mutableStateListOf<RecipeItem>() }
    val filteredRecipes = remember { mutableStateListOf<RecipeItem>() }
    val scope = rememberCoroutineScope()

    var isLoading by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Todos") }

    val filters = listOf("Todos", "Desayuno", "Comida", "Cena", "Postre")

    val filterKeywords = mapOf(
        "Desayuno" to listOf("breakfast", "pancake", "cereal", "toast", "yogurt", "smoothie"),
        "Comida" to listOf("lunch", "bowl", "wrap", "rice", "salad", "chicken", "beans"),
        "Cena" to listOf("dinner", "steak", "fish", "pasta", "meatballs", "pizza"),
        "Postre" to listOf("dessert", "cake", "cookies", "ice cream", "brownie", "tart")
    )

    // Mapping personalizado para mealPlan -> etiquetas
    val mealPlanQueryMap = mapOf(
        "alimentación equilibrada" to "balanced healthy light",
        "alta en proteínas" to "high protein lean muscle chicken egg",
        "vegana" to "vegan tofu lentils quinoa plant-based",
        "vegetariana" to "vegetarian vegetables cheese eggplant grains",
        "baja en carbohidratos" to "low carb keto lean salad chicken",
        "no tengo preferencia" to "popular easy"
    )

    LaunchedEffect(Unit) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return@LaunchedEffect
        val doc = FirebaseFirestore.getInstance().collection("Users").document(uid).get().await()
        val mealPlan = doc.getString("mealPlan")?.lowercase()?.trim() ?: "no tengo preferencia"
        val goals = (doc["goals"] as? List<*>)?.mapNotNull { it?.toString()?.lowercase()?.trim() } ?: emptyList()

        val dietParam = when (mealPlan) {
            "vegana" -> "vegan"
            "vegetariana" -> "vegetarian"
            "alta en proteínas" -> "high protein"
            "baja en carbohidratos" -> "low carb"
            else -> null // para "alimentación equilibrada" o "sin preferencia"
        }

        val queryParam = when (mealPlan) {
            "alta en proteínas" -> "high protein"
            "vegana" -> "vegan"
            "vegetariana" -> "vegetarian"
            "baja en carbohidratos" -> "low carb"
            "alimentación equilibrada" -> "healthy"
            else -> "recipe"
        }

        val typeParam = when (selectedFilter.lowercase()) {
            "desayuno" -> "breakfast"
            "comida" -> "main course"
            "cena" -> "dinner"
            "postre" -> "dessert"
            else -> null
        }

        scope.launch {
            try {
                println("🔍 ENVIANDO A API → diet: $dietParam | query: $queryParam | type: $typeParam")

                val response = RetrofitClient.api.getRecipes(
                    apiKey = "7053a28dc4a1444b9d0dc58943b864dd",
                    diet = dietParam,
                    query = queryParam,
                    type = typeParam,
                    sort = "popularity", // puedes usar "random" si prefieres variedad
                    number = 10
                )

                println("🍽 RESULTADOS: ${response.results.size}")

                recipes.clear()
                recipes.addAll(response.results)
                applyFilters(recipes, searchQuery, selectedFilter, filterKeywords, filteredRecipes)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }


    Scaffold(
        containerColor = Color(0xFFFAF9F0),
        topBar = {
            TopAppBar(
                title = { Text("Nutrición", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = greenPrimary)
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = greenPrimary)
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        applyFilters(recipes, searchQuery, selectedFilter, filterKeywords, filteredRecipes)
                    },
                    placeholder = { Text("Buscar recetas...") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = greenPrimary,
                        unfocusedBorderColor = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.height(12.dp))

                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(filters) { filter ->
                        val selected = selectedFilter == filter
                        FilterChip(
                            selected = selected,
                            onClick = {
                                selectedFilter = filter
                                applyFilters(recipes, searchQuery, selectedFilter, filterKeywords, filteredRecipes)
                            },
                            label = { Text(filter) },
                            shape = RoundedCornerShape(20.dp),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = greenPrimary,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (filteredRecipes.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No se encontraron resultados",
                            color = Color.Gray,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                } else {
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(filteredRecipes) { recipe ->
                            Card(
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(Screen.RecipeDetail.createRoute(recipe.id))
                                    },
                                colors = CardDefaults.cardColors(containerColor = Color.White)
                            ) {
                                Row(modifier = Modifier.padding(8.dp)) {
                                    Image(
                                        painter = rememberAsyncImagePainter(recipe.image),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(90.dp)
                                            .padding(end = 8.dp)
                                    )
                                    Column {
                                        Text(recipe.title, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                                        if (!recipe.dishTypes.isNullOrEmpty()) {
                                            Text(
                                                text = recipe.dishTypes.joinToString(", ").replaceFirstChar { it.uppercase() },
                                                fontSize = 13.sp,
                                                color = Color.Gray
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun applyFilters(
    allRecipes: List<RecipeItem>,
    query: String,
    filter: String,
    filterKeywords: Map<String, List<String>>,
    result: MutableList<RecipeItem>
) {
    result.clear()
    val queryLower = query.lowercase()

    result.addAll(
        allRecipes.filter { recipe ->
            val matchesQuery = query.isBlank() || recipe.title.contains(queryLower, ignoreCase = true)
            val matchesFilter = when (filter) {
                "Todos" -> true
                else -> {
                    val keywords = filterKeywords[filter] ?: emptyList()
                    val titleLower = recipe.title.lowercase()
                    val tags = recipe.dishTypes?.map { it.lowercase() } ?: emptyList()

                    keywords.any { keyword ->
                        titleLower.contains(keyword) || tags.any { it.contains(keyword) }
                    }
                }
            }

            matchesQuery && matchesFilter
        }
    )
}
