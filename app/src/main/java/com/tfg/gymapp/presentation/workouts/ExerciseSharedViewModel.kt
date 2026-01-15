// com.tfg.gymapp.presentation.workouts.ExerciseSharedViewModel.kt
package com.tfg.gymapp.presentation.workouts

import androidx.lifecycle.ViewModel
import com.tfg.gymapp.data.model.ExerciseInfo

class ExerciseSharedViewModel : ViewModel() {
    var selectedExercise: ExerciseInfo? = null
}
