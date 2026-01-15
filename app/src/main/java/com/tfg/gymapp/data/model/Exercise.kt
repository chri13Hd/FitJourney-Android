package com.tfg.gymapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class ExerciseInfoResponse(
    val results: List<ExerciseInfo>
)

@Parcelize
data class ExerciseInfo(
    val id: Int,
    val category: Category?,
    val equipment: List<Equipment>,
    val images: List<ExerciseImage>,
    val license_author: String,
    val muscles: List<Muscle>?,
    val muscles_secondary: List<Muscle>?,
    val name: String?,
    val description: String?,
    val language: Int,
    val translations: List<ExerciseTranslation>
) : Parcelable

@Parcelize
data class ExerciseTranslation(
    val language: Int,
    val name: String?,
    val description: String?
) : Parcelable

@Parcelize
data class Category(
    val id: Int,
    val name: String?
) : Parcelable

@Parcelize
data class Muscle(
    val id: Int,
    val name: String
) : Parcelable

data class Exercise(
    val name: String,
    val description: String,
    val videoUrl: String,
    val reps: String,
    val type: ExerciseType // CALENTAMIENTO, PRINCIPAL o ESTIRAMIENTO
)

enum class ExerciseType {
    CALENTAMIENTO,
    PRINCIPAL,
    ESTIRAMIENTO
}

fun ExerciseInfo.getMainImageUrl(): String? {
    return this.images.firstOrNull { it.is_main }?.image ?: this.images.firstOrNull()?.image
}
