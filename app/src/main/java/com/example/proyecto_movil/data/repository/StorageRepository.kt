package com.example.proyecto_movil.data.repository

import android.net.Uri
import com.example.proyecto_movil.data.datasource.AuthRemoteDataSource
import com.example.proyecto_movil.data.datasource.StorageRemoteDataSource
import jakarta.inject.Inject

class StorageRepository @Inject constructor(
    private val storage: StorageRemoteDataSource,
    private val auth: AuthRemoteDataSource
) {

    suspend fun uploadProfileImage(uri: Uri): Result<String> {
        return try {
            val userId = auth.currentUser?.uid ?: return Result.failure(
                Exception(
                    "No se encontro un usuario"
                )
            )
            val path = "ProfileImages/$userId.jpg"
            val url = storage.uploadImage(path, uri)
            Result.success(url)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
