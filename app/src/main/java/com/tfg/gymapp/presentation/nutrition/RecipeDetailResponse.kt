package com.tfg.gymapp.presentation.nutrition

import com.google.gson.annotations.SerializedName

data class RecipeDetailResponse(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String,
    val extendedIngredients: List<Ingredient>,
    val nutrition: NutritionInfo?,
    @SerializedName("instructions")
    val instructions: String
)

data class Ingredient(
    val name: String,
    val amount: Double,
    val unit: String
)

data class NutritionInfo(
    val nutrients: List<Nutrient>
)

data class Nutrient(
    val name: String,
    val amount: Double,
    val unit: String
)
