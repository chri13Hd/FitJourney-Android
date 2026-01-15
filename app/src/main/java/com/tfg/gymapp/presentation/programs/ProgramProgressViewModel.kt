package com.tfg.gymapp.presentation.programs

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProgramProgressViewModel : ViewModel() {

    private val _progress = MutableStateFlow<Map<String, Int>>(emptyMap())
    val progress: StateFlow<Map<String, Int>> = _progress

    fun loadProgress(programName: String) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("UserProgress").document(uid)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val snapshot = userRef.get().await()
                val completedDaysMap = snapshot.get("completedDays") as? Map<String, List<Long>> ?: emptyMap()
                val completedDays = completedDaysMap[programName]?.map { it.toInt() } ?: emptyList()

                val maxDayCompleted = completedDays.maxOrNull() ?: 0

                val newMap = _progress.value.toMutableMap()
                newMap[programName] = maxDayCompleted
                _progress.value = newMap

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun saveProgress(programName: String, dayNumber: Int) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance()
        val userRef = db.collection("UserProgress").document(uid)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                db.runTransaction { transaction ->
                    val snapshot = transaction.get(userRef)

                    val completedDaysMap = snapshot.get("completedDays") as? Map<String, List<Long>> ?: emptyMap()
                    val newDays = completedDaysMap[programName]?.map { it.toInt() }?.toMutableList() ?: mutableListOf()
                    if (!newDays.contains(dayNumber)) newDays.add(dayNumber)

                    val totalDays = when (programName.lowercase()) {
                        "pérdida de peso" -> 28
                        "ganar masa muscular" -> 42
                        "flexibilidad" -> 21
                        "mejorar resistencia" -> 35
                        "comer más saludable" -> 28
                        "tonificar cuerpo" -> 28
                        else -> 30
                    }

                    val percentage = (newDays.size.toDouble() / totalDays.toDouble()) * 100.0

                    val updatedCompletedDays = completedDaysMap.toMutableMap()
                    updatedCompletedDays[programName] = newDays.map { it.toLong() }

                    val updatedTotalDays = snapshot.get("totalDaysByProgram") as? Map<String, Long> ?: emptyMap()
                    val newTotalDays = updatedTotalDays.toMutableMap()
                    newTotalDays[programName] = totalDays.toLong()

                    val updatedPercentages = snapshot.get("percentageByProgram") as? Map<String, Double> ?: emptyMap()
                    val newPercentages = updatedPercentages.toMutableMap()
                    newPercentages[programName] = percentage

                    val completedPrograms = snapshot.get("completedPrograms") as? List<String> ?: emptyList()
                    val newCompleted = if (newDays.size == totalDays && !completedPrograms.contains(programName)) {
                        completedPrograms + programName
                    } else {
                        completedPrograms
                    }

                    transaction.set(userRef, mapOf(
                        "activeProgram" to programName,
                        "completedDays" to updatedCompletedDays,
                        "totalDaysByProgram" to newTotalDays,
                        "percentageByProgram" to newPercentages,
                        "completedPrograms" to newCompleted
                    ), SetOptions.merge())
                }.await()

                // ✅ Actualizar el progreso localmente tras guardar
                val current = _progress.value.toMutableMap()
                val currentCompleted = current[programName] ?: 0
                if (dayNumber > currentCompleted) {
                    current[programName] = dayNumber
                    _progress.value = current
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
