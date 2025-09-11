package com.example.proyecto_movil.data.injection

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FireBaseHiltModule {
    @Provides
    fun auth(): FirebaseAuth = Firebase.auth
}