package com.tfg.gymapp.data.programcontent

val weightLossProgram = listOf(
    listOf("Cardio: 30 mins", "HIIT: 15 mins", "Estiramientos"),
    listOf("Entrenamiento con pesas", "Cardio: 20 mins"),
    listOf("Yoga", "Caminata larga"),
    listOf("Circuito full body", "HIIT: 20 mins", "Estiramientos")
)

val muscleGainProgram = listOf(
    listOf("Pecho y tríceps", "Abdomen"),
    listOf("Espalda y bíceps", "Cardio suave"),
    listOf("Piernas y glúteos", "Estiramientos"),
    listOf("Full body", "HIIT", "Abdomen"),
    listOf("Torso", "Cardio suave"),
    listOf("Piernas", "Yoga o descanso activo")
)

val flexibilityProgram = listOf(
    listOf("Yoga suave", "Movilidad articular"),
    listOf("Estiramientos dinámicos", "Respiración consciente"),
    listOf("Yoga avanzado", "Meditación guiada")
)

val enduranceProgram = listOf(
    listOf("Cardio: 45 mins", "Circuito funcional"),
    listOf("HIIT", "Cardio suave"),
    listOf("Resistencia con peso corporal", "Abdomen intenso"),
    listOf("Correr en intervalos", "Estiramientos largos"),
    listOf("Ciclismo indoor", "Core stability")
)

val healthyEatingProgram = listOf(
    listOf("Desayuno balanceado", "Evitar procesados"),
    listOf("Aumentar frutas y verduras", "Cocinar casero"),
    listOf("Control de porciones", "Beber más agua"),
    listOf("Planificación de comidas", "Snacks saludables")
)

val bodyToningProgram = listOf(
    listOf("Peso corporal: full body", "Bandas elásticas"),
    listOf("HIIT + Tonificación", "Estiramientos"),
    listOf("Core", "Glúteos y piernas"),
    listOf("Full body + Estiramiento profundo")
)

val programContentMap = mapOf(
    "Bajar de peso" to weightLossProgram,
    "Ganar masa muscular" to muscleGainProgram,
    "Flexibilidad" to flexibilityProgram,
    "Mejorar resistencia" to enduranceProgram,
    "Comer más saludable" to healthyEatingProgram,
    "Tonificar cuerpo" to bodyToningProgram
)
