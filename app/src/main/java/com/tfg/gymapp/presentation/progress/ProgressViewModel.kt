package com.tfg.gymapp.presentation.progress

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

data class ProgressState(
    val fallbackGoal: String? = null,
    val percentageByProgram: Map<String, Double> = emptyMap(),
    val userWeight: Double? = null,
    val caloriesBurned: Double? = null,
    val isLoading: Boolean = true,
    val waterIntake: Int = 0
)

@RequiresApi(Build.VERSION_CODES.O)
class ProgressViewModel : ViewModel() {

    private val _state = MutableStateFlow(ProgressState())
    val state: StateFlow<ProgressState> = _state

    init {
        loadProgressData()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadProgressData() {
        viewModelScope.launch {
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val db = FirebaseFirestore.getInstance()

            try {
                val progressDoc = db.collection("UserProgress").document(uid).get().await()
                val userDoc = db.collection("Users").document(uid).get().await()

                val rawPercentages = progressDoc.get("percentageByProgram") as? Map<String, Number> ?: emptyMap()
                val percentageByProgram = rawPercentages.mapValues { it.value.toDouble() }

                val fallbackGoal = (userDoc.get("goals") as? List<*>)?.firstOrNull() as? String
                val userWeight = progressDoc.getDouble("weight")
                val caloriesBurned = progressDoc.getDouble("caloriesBurned")
                val waterIntake = progressDoc.getLong("waterIntake")?.toInt() ?: 0

                _state.value = ProgressState(
                    fallbackGoal = fallbackGoal,
                    percentageByProgram = percentageByProgram,
                    userWeight = userWeight,
                    caloriesBurned = caloriesBurned,
                    waterIntake = waterIntake,
                    isLoading = false
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = ProgressState(isLoading = false)
            }
        }
    }

    fun incrementWaterIntake() {
        viewModelScope.launch {
            val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return@launch
            val db = FirebaseFirestore.getInstance()

            val newIntake = _state.value.waterIntake + 1
            db.collection("UserProgress").document(uid).update("waterIntake", newIntake)
                .addOnSuccessListener {
                    _state.value = _state.value.copy(waterIntake = newIntake)
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }
        }
    }
}
