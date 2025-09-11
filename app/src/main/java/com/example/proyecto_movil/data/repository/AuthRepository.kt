package com.example.proyecto_movil.data.repository

import com.example.proyecto_movil.data.datasource.AuthRemoteDataSource
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import jakarta.inject.Inject

class AuthRepository @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource
) {

    //Obtenemos el usuario
    val currentUser: FirebaseUser?
        get() = authRemoteDataSource.currentUser

    suspend fun signIn(email: String, password: String): Result<Unit> {
        return try {
            authRemoteDataSource.signIn(email, password)
            Result.success(Unit)
        }
        catch (e: FirebaseAuthInvalidCredentialsException){
            Result.failure(Exception("Credenciales incorrectas"))
        } catch (e: FirebaseAuthInvalidUserException) {
            Result.failure(Exception("El usuario no existe"))
        }
        catch (e: Exception) {
            Result.failure(Exception("Error al iniciar sesion"))
        }

    }

    suspend fun signUp(email: String, password: String): Result<Unit> {
        try {
            authRemoteDataSource.signUp(email, password)
            return Result.success(Unit)
        }
        catch (e: Exception) {
            return Result.failure(e)
        }

    }

    fun signOut() {
        authRemoteDataSource.signOut()
    }



}