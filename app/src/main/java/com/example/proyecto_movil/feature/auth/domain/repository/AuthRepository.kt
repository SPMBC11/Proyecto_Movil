package com.example.proyecto_movil.feature.auth.domain.repository

import com.example.proyecto_movil.feature.auth.domain.model.User

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun logout(): Result<Unit>
}

