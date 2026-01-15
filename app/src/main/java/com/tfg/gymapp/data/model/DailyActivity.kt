// DailyActivity.kt
package com.tfg.gymapp.data.model

data class DailyActivity(
    val dayNumber: Int,
    val title: String,
    val description: String,
    val videoUrl: String,
    val durationInSeconds: Int,
    val suggestedRecipeId: Int? = null
)
