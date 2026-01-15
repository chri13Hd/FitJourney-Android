package com.tfg.gymapp.data.api

import com.tfg.gymapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogApiService {
    @GET("v2/everything")
    suspend fun getFitnessArticles(
        @Query("q") query: String = "fitness muscle workout training gym exercise strength protein health",
        @Query("language") language: String = "en",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String
    ): NewsResponse
}