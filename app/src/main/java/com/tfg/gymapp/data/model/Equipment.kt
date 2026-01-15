package com.tfg.gymapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Equipment(
    val id: Int,
    val name: String
) : Parcelable
