package com.tfg.gymapp.data.model

import android.health.connect.datatypes.ActiveCaloriesBurnedRecord

data class UserProgress(
    val activeProgram: String = "",
    val completedPrograms: List<String> = emptyList(),
    val completedDays: Map<String, List<Int>> = emptyMap(),
    val totalDaysByProgram: Map<String, Int> = emptyMap(),
    val percentageByProgram: Map<String, Double> = emptyMap(),
    val weight: Double? = null,
    val caloriesBurned: Int? = null
)
