package com.tfg.gymapp.data.api

import com.tfg.gymapp.data.model.ExerciseImage
import com.tfg.gymapp.data.model.ExerciseInfo
import com.tfg.gymapp.data.model.WgerImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WgerApiService {
    @GET("exerciseinfo/")
    suspend fun getExercises(
        @Query("language") language: Int = 4, // Español
        @Query("limit") limit: Int = 200,
        @Query("status") status: Int = 2 // only approved
    ): WgerExerciseResponse

    interface WgerApiService {
        @GET("exerciseimage/")
        suspend fun getExerciseImages(
            @Query("exercise") exerciseId: Int
        ): ExerciseImageResponse
    }


data class WgerExerciseResponse(
    val results: List<ExerciseInfo>
)
    data class ExerciseImageResponse(
        val results: List<ExerciseImage>
    )
}
