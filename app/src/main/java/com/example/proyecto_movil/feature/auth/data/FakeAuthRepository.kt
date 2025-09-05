package com.example.proyecto_movil.feature.auth.data

import com.example.proyecto_movil.feature.auth.domain.model.User
import com.example.proyecto_movil.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.delay

class FakeAuthRepository : AuthRepository {
    override suspend fun login(email: String, password: String): Result<User> {
        // Simulate network latency
        delay(400)
        if (email.isBlank() || password.isBlank()) {
            return Result.failure(IllegalArgumentException("Credenciales inválidas"))
        }
        // Accept any non-empty credentials for now
        return Result.success(
            User(
                id = "1",
                username = email.substringBefore('@').ifBlank { "user" },
                email = email
            )
        )
    }

    override suspend fun logout(): Result<Unit> {
        delay(150)
        return Result.success(Unit)
    }
}

