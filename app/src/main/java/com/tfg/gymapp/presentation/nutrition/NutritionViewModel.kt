package com.tfg.gymapp.presentation.nutrition

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tfg.gymapp.data.api.RecipeItem
import com.tfg.gymapp.data.api.RetrofitClient
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class NutritionViewModel : ViewModel() {
    val recipes = mutableStateListOf<RecipeItem>()

    init {
        loadRecipes()
    }

    fun loadRecipes() {
        viewModelScope.launch {
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val doc = FirebaseFirestore.getInstance().collection("Users").document(uid).get().await()
            val mealPlan = doc.getString("mealPlan")?.lowercase()?.trim() ?: "no tengo preferencia"

            val dietParam = when (mealPlan) {
                "vegana" -> "vegan"
                "vegetariana" -> "vegetarian"
                "alta en proteínas" -> "high protein"
                "baja en carbohidratos" -> "low carb"
                else -> null
            }

            val queryParam = when (mealPlan) {
                "alta en proteínas" -> "high protein"
                "vegana" -> "vegan"
                "vegetariana" -> "vegetarian"
                "baja en carbohidratos" -> "low carb"
                "alimentación equilibrada" -> "healthy"
                else -> "recipe"
            }

            try {
                val response = RetrofitClient.api.getRecipes(
                    apiKey = "7053a28dc4a1444b9d0dc58943b864dd",
                    diet = dietParam,
                    query = queryParam,
                    type = null,
                    sort = "popularity",
                    number = 10,
                    addRecipeInformation = true,
                    addRecipeNutrition = true
                )
                recipes.clear()
                recipes.addAll(response.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
