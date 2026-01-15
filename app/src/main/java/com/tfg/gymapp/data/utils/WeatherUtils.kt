package com.tfg.gymapp.data.utils

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import kotlin.random.Random

suspend fun fetchWeather(
    city: String,
    weather: MutableState<String>,
    temperature: MutableState<String>,
    motivationalMessage: MutableState<String>,
    userGoals: List<String>
) {
    val apiKey = "90a8bebe56a35a6ba86dee5dce05bdc2" // Sustituye con tu API KEY real
    val client = OkHttpClient()

    val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey&units=metric&lang=es"
    val request = Request.Builder().url(url).build()

    val muscleMessages = listOf(
        "¡Perfecto día para ganar músculo!",
        "¡A romper tus límites hoy!",
        "¡Tu esfuerzo construye tu fuerza!"
    )

    val weightLossMessages = listOf(
        "¡Hoy puedes quemar muchas calorías!",
        "¡Cada paso te acerca a tu meta!",
        "¡Haz que hoy cuente para tu transformación!"
    )

    val flexibilityMessages = listOf(
        "La constancia mejora tu movilidad",
        "¡Un cuerpo flexible es un cuerpo feliz!",
        "¡Estírate, respira, avanza!"
    )

    val defaultMessages = listOf(
        "¡Hoy es un gran día para progresar!",
        "¡Tú puedes con todo!",
        "¡Sigue con tu ritmo, estás más cerca cada día!"
    )

    withContext(Dispatchers.IO) {
        try {
            val response = client.newCall(request).execute()
            response.body?.string()?.let { body ->
                val json = JSONObject(body)
                val main = json.getJSONObject("main")
                val weatherArray = json.getJSONArray("weather")
                val weatherDescription = weatherArray.getJSONObject(0).getString("description")
                val temp = main.getDouble("temp")

                weather.value = weatherDescription
                temperature.value = temp.toInt().toString()

                motivationalMessage.value = when {
                    userGoals.any { it.contains("masa", ignoreCase = true) } ->
                        muscleMessages.random()
                    userGoals.any { it.contains("peso", ignoreCase = true) } ->
                        weightLossMessages.random()
                    userGoals.any { it.contains("flexibilidad", ignoreCase = true) } ->
                        flexibilityMessages.random()
                    else -> defaultMessages.random()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            weather.value = "Sin conexión"
            temperature.value = "--"
            motivationalMessage.value = "No se pudo obtener el clima. ¡Sigue con tu entrenamiento!"
        }
    }
}
