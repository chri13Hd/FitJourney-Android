package com.tfg.gymapp.presentation.workouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfg.gymapp.data.api.RetrofitWgerClient
import com.tfg.gymapp.data.model.ExerciseInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExerciseViewModel : ViewModel() {

    private val _exercises = MutableStateFlow<List<ExerciseInfo>>(emptyList())
    val exercises: StateFlow<List<ExerciseInfo>> = _exercises

    fun loadExercisesByGoal(goal: String) {
        viewModelScope.launch {
            try {
                println("🔁 Obteniendo ejercicios desde Wger...")

                val responseEs = RetrofitWgerClient.api.getExercises(language = 4).results
                val responseEn = RetrofitWgerClient.api.getExercises(language = 2).results

                val translatedList = responseEs.map { es ->
                    val fallback = responseEn.find { it.id == es.id }
                    ExerciseInfo(
                        id = es.id,
                        category = es.category,
                        equipment = es.equipment,
                        images = es.images.ifEmpty { fallback?.images ?: emptyList() },
                        license_author = es.license_author ?: fallback?.license_author ?: "Desconocido",
                        muscles = es.muscles?.ifEmpty { fallback?.muscles ?: emptyList() } ?: emptyList(),
                        muscles_secondary = es.muscles_secondary?.ifEmpty { fallback?.muscles_secondary ?: emptyList() } ?: emptyList(),
                        name = es.name ?: fallback?.name ?: "Ejercicio sin nombre",
                        description = es.description ?: fallback?.description ?: "Sin descripción",
                        language = es.language,
                        translations = if (es.translations.isNotEmpty()) es.translations else fallback?.translations ?: emptyList()
                    )
                }

                val extras = responseEn.filter { en -> translatedList.none { it.id == en.id } }
                val merged = translatedList + extras

                // Nuevas categorías dinámicas
                val homeCategories = listOf(9, 11) // Bodyweight y Home training
                val clubCategories = listOf(8, 10) // Strength machines y Free weights

                val filtered = merged.filter { ex ->
                    val isHome = ex.category?.id in homeCategories
                    val isClub = ex.category?.id in clubCategories
                    val hasUsefulContent = ex.images.isNotEmpty() &&
                            ex.name?.isNotBlank() == true &&
                            ex.description?.isNotBlank() == true

                    when (goal.lowercase()) {
                        "club" -> isClub && hasUsefulContent
                        "home" -> isHome && hasUsefulContent
                        "programs" -> ex.translations.any { it.name?.isNotBlank() == true } &&
                                ex.translations.any { it.description?.isNotBlank() == true } &&
                                ex.images.isNotEmpty()
                        else -> false
                    }
                }

                _exercises.value = filtered
                println("✅ Ejercicios cargados: ${filtered.size}")

            } catch (e: Exception) {
                println("❌ Error al cargar ejercicios: ${e.localizedMessage}")
                e.printStackTrace()
            }
        }
    }
}