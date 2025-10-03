package com.example.proyecto_movil.data.datasource

import com.example.proyecto_movil.data.ReviewInfo

interface ReviewRemoteDataSource {
    suspend fun getAllReviews(): List<ReviewInfo>
}