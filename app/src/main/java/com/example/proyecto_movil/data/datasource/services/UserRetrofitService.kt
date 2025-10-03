package com.example.proyecto_movil.data.datasource.services

import com.example.proyecto_movil.data.dtos.ReviewDto
import retrofit2.http.GET
import retrofit2.http.Path

interface UserRetrofitService {
    @GET("users/{id}/reviews")
    suspend fun getUserReviews(@Path("id") userId: String): List<ReviewDto>
}

@GET("users/{userId}")
suspend fun getUserById(@Path("userId") userId: String):
}