package com.tfg.gymapp.data.api

import com.tfg.gymapp.presentation.nutrition.RecipeDetailResponse
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NutritionApiService {
    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String,
        @Query("diet") diet: String?,
        @Query("query") query: String?,
        @Query("type") type: String? = null,
        @Query("sort") sort: String = "popularity",
        @Query("number") number: Int = 20,
        @Query("addRecipeInformation") addRecipeInformation: Boolean = true,
        @Query("addRecipeNutrition") addRecipeNutrition: Boolean = true
    ): RecipeResponse


    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrition: Boolean = true
    ): RecipeDetailResponse
}

@Serializable
data class RecipeResponse(
    val results: List<RecipeItem>
)

@Serializable
data class RecipeItem(
    val id: Int,
    val title: String,
    val image: String,
    val dishTypes: List<String>?,
    val ingredients: List<String>?,
    val nutrition: NutritionPreview? // solo si quieres mostrar valores estimados
)

@Serializable
data class NutritionPreview(
    val calories: String,
    val fat: String,
    val carbs: String,
    val protein: String
)