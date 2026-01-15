package com.tfg.gymapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExerciseImage(
    val id: Int,
    val image: String,
    val is_main: Boolean,
    val status: String,
    val exercise: Int
) : Parcelable

data class WgerImageResponse(
    val results: List<ExerciseImage>
)
