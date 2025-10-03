package com.example.proyecto_movil.data.datasource

interface UserRemoteDataSource {

    suspend fun getUserById(id: String): Unit
    suspend fun getUserReviews(id: String): Unit
}